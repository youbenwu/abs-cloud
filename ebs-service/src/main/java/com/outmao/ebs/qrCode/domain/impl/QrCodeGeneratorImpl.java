package com.outmao.ebs.qrCode.domain.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.oss.OssService;
import com.outmao.ebs.qrCode.domain.QrCodeGenerator;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;


@Slf4j
@Component
public class QrCodeGeneratorImpl implements QrCodeGenerator {

    static HashMap<String,BufferedImage> imageCache=new HashMap<>();

    static HashMap<EncodeHintType,Comparable> hints=new HashMap<>();
    {
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//指定字符编码为“utf-8”
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//指定二维码的纠错等级为中
        hints.put(EncodeHintType.MARGIN,1);//指定二维码边距
    }

    static HashMap<EncodeHintType,Comparable> getHints(GenerateQrCodeDTO request){
        if(request.getMargin()!=null){
            HashMap<EncodeHintType,Comparable> _hints=new HashMap<>();
            _hints.putAll(hints);
            _hints.put(EncodeHintType.MARGIN,request.getMargin());
            return _hints;
        }
        return hints;
    }


    @Autowired
    private OssService ossService;


    @Value("${web.static-path}")
    private String savePath;

    @Value("${config.base-url}")
    private String baseUrl;

    @Value("${config.use-oss}")
    private boolean useOss;

    private String qrCodePath(String fileName) {
        return "qrCode/" + fileName;
    }

    @Override
    public String generateQrCode(GenerateQrCodeDTO request) {
        String path = this.qrCodePath(UUID.randomUUID().toString() + ".png");
        String filePath=this.savePath + path;
        this.generateQrCode(request, filePath);
        if(useOss){
            try{
                // 上传文件流。
                String url= ossService.putObject(path,filePath);
                return url;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return baseUrl + "/" + path;
    }


    @Override
    public void generateQrCode(GenerateQrCodeDTO request, String outputFile) {
        try {
            File file = new File(outputFile);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream stream = new FileOutputStream(file);
            this.generateQrCode(request, stream);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("生成二维码出错", e);
            throw new BusinessException("生成二维码出错", e.getMessage());
        }
    }


    @Override
    public void generateQrCode(GenerateQrCodeDTO request, OutputStream outputStream) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(
                    request.getCode(),
                    BarcodeFormat.QR_CODE,
                    request.getWidth(),
                    request.getHeight(),
                    getHints(request)
            );
            if(StringUtils.isEmpty(request.getLogo())) {
                MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
            }else{
                BufferedImage bufferedImage=editImage(bitMatrix,request);
                ImageIO.write(bufferedImage,"png",outputStream);
            }
        } catch (Exception e) {
            log.error("生成二维码出错", e);
            throw new BusinessException("生成二维码出错", e.getMessage());
        }
    }


    private BufferedImage editImage(BitMatrix bitMatrix, GenerateQrCodeDTO request){

        BufferedImage logoImage=loadImage(request.getLogo(),request.getWidth()/5,request.getHeight()/5);
        int width=request.getWidth();
        int height=request.getHeight();

        MatrixToImageConfig config = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
        BufferedImage bufferedImage= MatrixToImageWriter.toBufferedImage(bitMatrix,config);
        Graphics2D g2 = bufferedImage.createGraphics();
        //画LOGO
        g2.drawImage(logoImage, width / 5 * 2, height / 5 * 2, width / 5, height / 5, null);
        //画边框
        if(!StringUtils.isEmpty(request.getLogoBorderColor())) {
            g2.setColor(Color.decode(request.getLogoBorderColor()));
            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new RoundRectangle2D.Float(width / 5 * 2, height / 5 * 2, width / 5, height / 5, 20, 20));
        }
        g2.dispose();
        //g2.setColor(Color.white);
        //g2.setBackground(Color.white);
        bufferedImage.flush();
        return bufferedImage;
    }


    private BufferedImage loadImage(String path, int width, int height){
        if(path==null||path.isEmpty())
            return null;
        BufferedImage image=imageCache.get(path);
        if(image!=null)
            return image;
        String url=path;
        try {
            if(url.startsWith("http")){
                if(url.startsWith("http://oss")){
                    url=url+"?x-oss-process=image/resize,m_fill,h_"+width+",w_"+height;
                }
                image= ImageIO.read(new URL(url));
            }else if(url.startsWith("classpath:")){
                image=ImageIO.read(ResourceUtils.getFile(url));
            }else{
                image=ImageIO.read(ResourceUtils.getFile(url));
            }
            imageCache.put(path,image);
        }catch (Exception e){
            log.error("生成二维码出错", e);
            throw new BusinessException("生成二维码出错", e.getMessage());
        }
        return image;
    }



}
