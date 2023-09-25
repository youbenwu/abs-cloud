package com.outmao.ebs.wallet.pay.wechatpay.common;


import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class VerdureRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;
    private byte[] bytes;
    private WrappedServletInputStream  wrappedServletInputStream;
    public VerdureRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.orgRequest = request;
        //读取输入流的请求参数，保存到bytes中
        bytes = IOUtils.toByteArray(request.getInputStream());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        this.wrappedServletInputStream = new WrappedServletInputStream(byteArrayInputStream);

        //把post参数重新写入请求流
        reWriteInputStream();
    }
    public void setRequestParams(String json) {
        wrappedServletInputStream.setStream(new ByteArrayInputStream(json.getBytes()));
    }
    /**
     * 把参数重新写进请求里
     */
    public void reWriteInputStream() {
        wrappedServletInputStream.setStream(new ByteArrayInputStream(bytes != null ? bytes : new byte[0]));
    }
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return wrappedServletInputStream;
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(wrappedServletInputStream));
    }
    /**
     * 获取post参数
     */
    public String getRequestParams() throws IOException {
        return new String(bytes, this.getCharacterEncoding());
    }
    //清洗参数，防止xss注入
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = xssEncode(values[i]);
        }
        return encodedValues;
    }
    public String getParameter(String name){
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }
    private static String xssEncode(String s){
        return s;
    }
    public HttpServletRequest getOrgRequest(){
        return this.orgRequest;
    }
    private class WrappedServletInputStream extends ServletInputStream {
        public void setStream(InputStream stream) {
            this.stream = stream;
        }
        private InputStream stream;
        public WrappedServletInputStream(InputStream stream) {
            this.stream = stream;
        }
        public int read() throws IOException {
            return stream.read();
        }
        public boolean isFinished() {
            return true;
        }
        public boolean isReady() {
            return true;
        }
        public void setReadListener(ReadListener readListener) {
        }
    }
}


