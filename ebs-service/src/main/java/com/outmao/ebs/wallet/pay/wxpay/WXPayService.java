package com.outmao.ebs.wallet.pay.wxpay;

import java.util.Map;

public interface WXPayService {
	
	/*
	 * 生成APP支付订单信息 直接给客户端请求，无需再做处理。
	 */
	public Map<String, String> unifiedOrder(String outTradeNo, String body, double totalAmount, String clientIp);

	/*
	 * 退款。
	 */
	public Map<String, String> refund(String outTradeNo, double totalAmount);
	
	/*
	 * 关闭交易。
	 */
	public Map<String, String> closeOrder(String outTradeNo);
	
	/*
	 * 查交易。
	 */
	public Map<String, String> orderQuery(String outTradeNo);
	
	/*
	 * 查交易。
	 */
	public Map<String, String> refundQuery(String outTradeNo);

}
