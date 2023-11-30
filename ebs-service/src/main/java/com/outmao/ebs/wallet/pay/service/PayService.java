package com.outmao.ebs.wallet.pay.service;


import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.TradePayToDTO;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.dto.PayPrepareDTO;
import com.outmao.ebs.wallet.pay.dto.PayWalletDTO;
import com.outmao.ebs.wallet.vo.TradeVO;

public interface PayService {


	public void addStatusListener(TradeStatusListener listener);

	/**
     *
     * 创建交易
     *
     * */
	public TradeVO tradePrepare(TradePrepareDTO request);


	/**
	 *
	 * 获取APP支付订单信息 直接给客户端请求，无需再做处理
	 *
	 * */
	public Object payPrepare(PayPrepareDTO request);

	/**
	 *
	 * 钱包方式支付
	 *
	 * */
	public Trade payWallet(PayWalletDTO request);


	/**
	 *
	 * 付款到对方钱包、同一交易可以分多次付款、可以付款给多个人、付款后不能退
	 *
	 * */
	public Trade tradePayTo(TradePayToDTO request);

	/**
	 *
	 * 完成交易
	 *
	 * */
	public Trade tradeFinish(String tradeNo);

	/**
	 *
	 * 退款
	 *
	 * */
	public Trade tradeRefund(String tradeNo);

	/**
	 *
	 * 关闭交易
	 *
	 * */
	public Trade tradeClose(String tradeNo);

	/**
	 *
	 * 交易查询
	 *
	 * */
	public Trade tradeQuery(String tradeNo);


	/**
	 *
	 * 单笔转账到支付宝账户，用于用户提现
	 *
	 * */
	public AlipayFundTransToaccountTransferResponse fundTransToaccountTransfer(String outBizNo, double amount, String payeeAccount, String payeeRealName, String remark) ;



}
