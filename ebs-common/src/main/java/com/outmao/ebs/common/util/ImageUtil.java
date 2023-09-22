package com.outmao.ebs.common.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class ImageUtil {

    /**
     * 请求图片地址, 返回的结果进行base64编码
     * @param imgUrl 图片地址
     * @return
     */
    public static String requestUrlToBase64(String imgUrl) {
        String result = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(imgUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 获取请求输入流
            InputStream inputStream = connection.getInputStream();
            // inputStream流数据转ByteArrayOutputStream
            int len = -1;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            // ByteArrayOutputStream编码成base64字符串
            result = new String(Base64.getEncoder().encode(out.toByteArray()));
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection != null){
                connection.disconnect();
            }
        }

        return result;
    }

}
