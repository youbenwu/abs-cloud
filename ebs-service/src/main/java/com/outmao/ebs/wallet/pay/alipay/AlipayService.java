package com.outmao.ebs.wallet.pay.alipay;


import com.alipay.api.response.*;

public interface AlipayService {


	/**
	 *
	 * 生成APP支付订单信息 直接给客户端请求，无需再做处理。
	 * 
	 */
	public AlipayTradeAppPayResponse tradeAppPay(String outTradeNo, double totalAmount,String subject, String body);


	/**
	 *
	 * 当面付:收银员通过收银台或商户后台调用支付宝接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
	 *
	 */
	public AlipayTradePrecreateResponse tradePrecreate(String outTradeNo, double totalAmount,String subject, String body);


	/**
	 *
	 * 退款。
	 *
	 */
	public AlipayTradeRefundResponse tradeRefund(String outTradeNo, double totalAmount);


	/**
	 *
	 * 关闭交易。
	 *
	 */
	public AlipayTradeCloseResponse tradeClose(String outTradeNo);

	/**
	 *
	 * 查询订单状态
	 *
	 */
	public AlipayTradeQueryResponse tradeQuery(String outTradeNo);


	/**
	 *
	 * 单笔转账到支付宝账户，用于用户提现
	 *
	 * */
	public AlipayFundTransToaccountTransferResponse fundTransToaccountTransfer(String outBizNo, double amount,  String payeeAccount, String payeeRealName, String remark) ;

	/**
	 *
	 *
	 * 单笔转账查询
	 *
	 * */
	public AlipayFundTransOrderQueryResponse fundTransOrderQuery(String outBizNo);



	
}
