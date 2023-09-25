package com.outmao.ebs.wallet.pay.alipay;


import com.alipay.api.response.*;

public interface AlipayService {


	/**
	 *
	 * 生成APP支付订单信息 直接给客户端请求，无需再做处理。
	 * 
	 */
	public AlipayTradeAppPayResponse tradeAppPay(String subject, String body, String outTradeNo, double totalAmount);

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
	 * 单笔转账到支付宝账户，用于用户提现
	 *
	 * */
	public AlipayFundTransToaccountTransferResponse fundTransToaccountTransfer(String outBizNo, double amount, String payerShowName, String payeeAccount, String payeeRealName, String remark) ;

	/**
	 *
	 *
	 * 单笔转账查询
	 *
	 * */
	public AlipayFundTransOrderQueryResponse fundTransOrderQuery(String outBizNo);



	
}
