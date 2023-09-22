package com.outmao.ebs.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtil {
    public Document get(String url, String charset) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36";
        URL url2 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)url2.openConnection();
        connection.setRequestMethod("GET");
        //是否允许缓存，默认true。
        connection.setUseCaches(Boolean.FALSE);
        //设置请求头信息
        connection.addRequestProperty("Connection", "close");
        connection.addRequestProperty("user-agent", userAgent);
        //设置连接主机超时（单位：毫秒）
        connection.setConnectTimeout(80000);
        //设置从主机读取数据超时（单位：毫秒）
        connection.setReadTimeout(80000);
        //开始请求
        try {
            Document doc = Jsoup.parse(connection.getInputStream(), charset, url);
            return doc;
        } catch (Exception e) {
            System.out.println("parse error: " + url);
        }
        return null;
    }

    public String get(String url) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36";
        URL url2 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)url2.openConnection();
        connection.setRequestMethod("GET");
        //是否允许缓存，默认true。
        connection.setUseCaches(Boolean.FALSE);
        //设置请求头信息
        connection.addRequestProperty("Connection", "close");
        connection.addRequestProperty("user-agent", userAgent);
        //设置连接主机超时（单位：毫秒）
        connection.setConnectTimeout(80000);
        //设置从主机读取数据超时（单位：毫秒）
        connection.setReadTimeout(80000);
        //开始请求

        InputStream inputStream=connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(
                inputStreamReader);

        StringBuffer buffer = new StringBuffer();
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 释放资源
        inputStream.close();
        connection.disconnect();
        return buffer.toString();
    }



}

