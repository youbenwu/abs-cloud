package com.outmao.ebs.wallet.pay.wxpay;


import com.outmao.ebs.wallet.pay.wxpay.config.WXPayClient;
import com.outmao.ebs.wallet.pay.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/wxpay")
public class WXPayNotify {
	
	@Autowired
	private WXPayClient wxPayClient;

    @Autowired
    private WXPayNotifyListener listener;

    
    /**
     * 
     * app支付结果回调
     * 
     * */
    
	@RequestMapping("/notify")
	public String notify(HttpServletRequest request) throws Exception{
		Map<String, String> requestMap = wxPayClient.getNotifyParameter(request);

		StringBuilder notifyBuild = new StringBuilder(
				"/****************************** 微信支付结果回调 ******************************/\n");
		requestMap.forEach((key, value) -> notifyBuild.append(key + "=" + value + "\n"));
		System.out.println(notifyBuild.toString());

		listener.notify(requestMap);
        // 商户处理退款通知参数后同步返回给微信参数
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", "SUCCESS");
        responseMap.put("return_msg", "OK");
        String responseXml = WXPayUtil.mapToXml(responseMap);
        return responseXml;
	}
	
	/**
     * 
     * 退款结果回调
     * 
     * */
	@RequestMapping("/refund/notify")
	public String refundNotify(HttpServletRequest request) throws Exception{
		Map<String, String> requestMap = wxPayClient.decodeRefundNotify(request);

		StringBuilder notifyBuild = new StringBuilder(
				"/****************************** 微信退款结果回调 ******************************/\n");
		requestMap.forEach((key, value) -> notifyBuild.append(key + "=" + value + "\n"));
		System.out.println(notifyBuild.toString());

		listener.notifyRefund(requestMap);
        // 商户处理退款通知参数后同步返回给微信参数
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", "SUCCESS");
        responseMap.put("return_msg", "OK");
        String responseXml = WXPayUtil.mapToXml(responseMap);
        return responseXml;
	}

}
