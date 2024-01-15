package com.outmao.ebs.studio.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.service.SettleService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.QyPadToOrderVO;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.mall.order.vo.ToOrderVO;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.qrCode.dto.ActivateQrCodeDTO;
import com.outmao.ebs.qrCode.dto.UpdateQrCodeDTO;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.studio.dto.GetQyMoviePlayableDTO;
import com.outmao.ebs.studio.dto.GetQyMovieQrcodeDTO;
import com.outmao.ebs.studio.dto.QyMovieOrderDTO;
import com.outmao.ebs.studio.dto.QyMovieQrcodeScanDTO;
import com.outmao.ebs.studio.entity.Movie;
import com.outmao.ebs.studio.entity.MovieEpisode;
import com.outmao.ebs.studio.entity.UserMovieEpisode;
import com.outmao.ebs.studio.service.MovieService;
import com.outmao.ebs.studio.service.QyMovieService;
import com.outmao.ebs.studio.vo.QyMoviePlayableVO;
import com.outmao.ebs.studio.vo.QyMovieQrcodeVO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.wallet.pay.dto.PayPrepareDTO;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.vo.TradeVO;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class QyMovieServiceImpl implements QyMovieService {


    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private SettleService settleService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private HotelDeviceService hotelDeviceService;

    @Autowired
    private ProductService productService;

    @Override
    public QyMovieQrcodeVO getQyMovieQrcodeVO(GetQyMovieQrcodeDTO request) {

        String code="https://qianyan-4gbx9tubd992d384-1323130392.tcloudbaseapp.com?action=qy_scan_movie&episodeId="+request.getEpisodeId()+"&userId="+request.getUserId();
        QrCode qrCode=qrCodeService.activateQrCode(new ActivateQrCodeDTO(code));

        QyMovieQrcodeVO vo=new QyMovieQrcodeVO();
        vo.setQrCodeId(qrCode.getId());
        vo.setQrCodeUrl(qrCode.getPath());
        vo.setEpisodeId(request.getEpisodeId());

        return vo;
    }


    @Override
    public QyMoviePlayableVO getQyMoviePlayableVO(GetQyMoviePlayableDTO request) {
        QrCode qrCode=qrCodeService.getQrCodeById(request.getQrCodeId());

        if(qrCode==null){
            throw new BusinessException("二维码不存在");
        }

        QyMoviePlayableVO vo=new QyMoviePlayableVO();
        vo.setEpisodeId(request.getEpisodeId());

        if(qrCode.getBusiness()==null){
            //未扫码
            vo.setStatus(0);
        }else{

            Long userId=Long.parseLong(qrCode.getBusiness());

            UserMovieEpisode episode=movieService.getUserMovieEpisode(userId,request.getEpisodeId());

            if(episode==null){
                //未购买
                vo.setStatus(1);
            }else{
                //已购买
                vo.setStatus(2);
            }

        }

        return vo;
    }

    @Override
    public void qyMovieQrcodeScan(QyMovieQrcodeScanDTO request) {
        QrCode qrCode=qrCodeService.getQrCodeById(request.getQrCodeId());

        if(qrCode==null){
            throw new BusinessException("二维码不存在");
        }


        UpdateQrCodeDTO updateQrCodeDTO=new UpdateQrCodeDTO();
        updateQrCodeDTO.setBusiness(request.getUserId().toString());
        updateQrCodeDTO.setId(qrCode.getId());
        updateQrCodeDTO.setUrl(qrCode.getUrl());

        qrCodeService.updateQrCode(updateQrCodeDTO);


    }

    @Override
    public QyPadToOrderVO qyMovieOrder(QyMovieOrderDTO request) {


        QrCode qrCode=qrCodeService.getQrCodeById(request.getQrCodeId());

        if(qrCode==null){
            throw new BusinessException("二维码不存在");
        }

        Long ownerId=null;
        if(qrCode.getBusiness()!=null){
            ownerId=Long.parseLong(qrCode.getBusiness());
        }

        HotelDevice device=hotelDeviceService.getHotelDeviceByUserId(request.getUserId());

        CreateSettleDTO settleDTO=new CreateSettleDTO();

        if(device!=null){
            settleDTO.setHotelId(device.getHotelId());
            settleDTO.setRoomNo(device.getRoomNo());
        }

        settleDTO.setUserId(request.getUserId());

        MovieEpisode episode=movieService.getMovieEpisodeById(request.getEpisodeId());
        Movie movie=movieService.getMovieById(episode.getMovieId());


        ProductSku sku=productService.getProductSku(movie.getProductId(),episode.getIndex()+"");

        CreateSettleProductDTO productDTO=new CreateSettleProductDTO();
        productDTO.setQuantity(1);
        productDTO.setSkuId(sku.getId());

        settleDTO.setProducts(new ArrayList<>());
        settleDTO.getProducts().add(productDTO);


        SettleVO settleVO=settleService.createSettle(settleDTO);

        ToOrderDTO toOrderDTO=new ToOrderDTO();
        toOrderDTO.setSettleId(settleVO.getId());
        toOrderDTO.setData(JSON.toJSONString(episode));
        if(request.getRemark()!=null) {
            toOrderDTO.setShops(new ArrayList<>());
            toOrderDTO.getShops().add(new ToOrderShopDTO());
            toOrderDTO.getShops().get(0).setShopId(settleVO.getShops().get(0).getShopId());
            toOrderDTO.getShops().get(0).setRemark(request.getRemark());
        }

        ToOrderVO toOrderVO=settleService.buy(toOrderDTO);

        OrderPayPrepareDTO payPrepareDTO=new OrderPayPrepareDTO();
        payPrepareDTO.setOrderNo(toOrderVO.getOrders().get(0));
        payPrepareDTO.setPayChannel(request.getPayChannel());
        payPrepareDTO.setOutPayType(request.getOutPayType());


        TradeVO tradeVO=orderService.payPrepare(payPrepareDTO);

        PayPrepareDTO payPrepareDTO1=new PayPrepareDTO();
        payPrepareDTO1.setTradeNo(tradeVO.getTradeNo());
        Object object=payService.payPrepare(payPrepareDTO1);

        log.info("迁眼平板支付信息{}",object);

        OrderVO order=orderService.getOrderVOByOrderNo(tradeVO.getTradeNo());

        QyPadToOrderVO qyPadToOrderVO=new QyPadToOrderVO();
        qyPadToOrderVO.setOrderNo(tradeVO.getTradeNo());
        qyPadToOrderVO.setData(object);
        qyPadToOrderVO.setOrder(order);

        if(object instanceof AlipayTradePrecreateResponse){
            AlipayTradePrecreateResponse ali=(AlipayTradePrecreateResponse)object ;
            qyPadToOrderVO.setQrCode(ali.getQrCode());
            if(ali.getQrCode()!=null) {
                qrCode=qrCodeService.activateQrCode(new ActivateQrCodeDTO(ali.getQrCode()));
                qyPadToOrderVO.setQrCodeUrl(qrCode.getPath());
            }
        }

        else if(object instanceof PrepayResponse){
            PrepayResponse wx=(PrepayResponse)object ;
            qyPadToOrderVO.setQrCodeUrl(wx.getCodeUrl());
        }



        String code="https://qianyan-4gbx9tubd992d384-1323130392.tcloudbaseapp.com?action=order_bind_owner&type="+order.getType()+"&orderNo="+order.getOrderNo();
        qrCode=qrCodeService.activateQrCode(new ActivateQrCodeDTO(code,qyPadToOrderVO.getOrderNo()));
        qyPadToOrderVO.setBindQrCodeUrl(qrCode.getPath());
        qyPadToOrderVO.setBindQrCodeId(qrCode.getId());

        if(ownerId!=null) {
            OrderBindOwnerDTO bindOwnerDTO = new OrderBindOwnerDTO();
            bindOwnerDTO.setUserId(ownerId);
            bindOwnerDTO.setOrderNo(order.getOrderNo());
            bindOwnerDTO.setQrCodeId(qrCode.getId());
            orderService.orderBindOwner(bindOwnerDTO);

        }
        return qyPadToOrderVO;

    }



}
