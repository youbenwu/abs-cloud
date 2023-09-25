package com.outmao.ebs.wallet.pay.alipay.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.pay.alipay.AlipayService;
import com.outmao.ebs.wallet.pay.alipay.config.AlipayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {

	@Autowired
	private AlipayClient alipayClient;

	@Autowired
	private AlipayProperties alipayProperties;


	@Override
	public AlipayTradeAppPayResponse tradeAppPay(String subject, String body, String outTradeNo, double totalAmount) {
		String amountS = String.format("%.2f", totalAmount);
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(body);
		model.setSubject(subject);
		model.setOutTradeNo(outTradeNo);
		model.setTotalAmount(amountS);
		// 30分钟后超时
		model.setTimeoutExpress("30m");
		model.setProductCode("QUICK_MSECURITY_PAY");

		request.setBizModel(model);
		request.setNotifyUrl(alipayProperties.getNotifyUrl());

		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			if (!response.isSuccess()) {
				throw new BusinessException(response.getMsg());
			}
			// 就是orderString 可以直接给客户端请求，无需再做处理。
			return response;
		} catch (AlipayApiException e) {
			throw new BusinessException(e.getMessage());
		}
	}


	@Override
	public AlipayTradeRefundResponse tradeRefund(String outTradeNo, double totalAmount) {
		String amountS = String.format("%.2f", totalAmount);
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		// 商户订单号
		model.setOutTradeNo(outTradeNo);
		// 退款金额
		model.setRefundAmount(amountS);
		// 退款原因
		model.setRefundReason("无理由退款");
		// 退款订单号(同一个订单可以分多次部分退款，当分多次时必传)
        // entity.setOutRequestNo(UUID.randomUUID().toString());
		request.setBizModel(model);

		try {
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			if (!response.isSuccess()) {
				throw new BusinessException(response.getMsg());
			}
			return response;
		} catch (AlipayApiException e) {
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	public AlipayTradeCloseResponse tradeClose(String outTradeNo) {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		AlipayTradeCloseModel model = new AlipayTradeCloseModel();
		model.setOutTradeNo(outTradeNo);
		request.setBizModel(model);

		try {
			AlipayTradeCloseResponse response = alipayClient.execute(request);
			if (!response.isSuccess()) {
				throw new BusinessException(response.getMsg());
			}
			return response;
		} catch (AlipayApiException e) {
			throw new BusinessException(e.getMessage());
		}

	}

	/**
	 * 交易状态：
	 * WAIT_BUYER_PAY（交易创建，等待买家付款）、
	 * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
	 * TRADE_SUCCESS（交易支付成功）、
	 * TRADE_FINISHED（交易结束，不可退款）
	 */
	@Override
	public AlipayTradeQueryResponse tradeQuery(String outTradeNo) {
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(outTradeNo);
		alipayRequest.setBizModel(model);

		try {
			AlipayTradeQueryResponse alipayResponse = alipayClient.execute(alipayRequest);

			if (!alipayResponse.isSuccess()) {
				log.error("查询支付宝交易状态失败\n" + "订单号：" + outTradeNo + "\n" + alipayResponse.toString());
				throw new BusinessException(alipayResponse.getMsg());
			}
			/**
			 * 交易状态：
			 * WAIT_BUYER_PAY（交易创建，等待买家付款）、
			 * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
			 * TRADE_SUCCESS（交易支付成功）、
			 * TRADE_FINISHED（交易结束，不可退款）
			 */
			return alipayResponse;
		} catch (AlipayApiException e) {
			log.error("查询支付宝交易状态失败\n" + "订单号：" + outTradeNo + "\n" + e.getMessage());
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	public AlipayFundTransToaccountTransferResponse fundTransToaccountTransfer(String outBizNo, double amount, String payerShowName, String payeeAccount, String payeeRealName, String remark) {
		String amountS = String.format("%.2f", amount);
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
		model.setPayerShowName(payerShowName);
		model.setAmount(amountS);
		model.setOutBizNo(outBizNo);
		model.setPayeeType("ALIPAY_LOGONID");
		model.setPayeeAccount(payeeAccount);
		model.setPayeeRealName(payeeRealName);
		model.setRemark(remark);
		request.setBizModel(model);
		try {
			AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);

			if (!response.isSuccess()) {
				log.error("单笔转账到支付宝账户错误\n" + "订单号：" + outBizNo + "\n" + response.toString());
				throw new BusinessException(response.getMsg()+"\n"+response.getSubMsg());
			}
            return response;
		} catch (AlipayApiException e) {
			log.error("单笔转账到支付宝账户错误", e);
			throw new BusinessException("转帐到支付宝出错，请联系管理员", e.getMessage());
		}
	}

	/**
	 * 
	 * status转账单据状态。 
	 * SUCCESS：成功（配合"单笔转账到银行账户接口"产品使用时, 同一笔单据多次查询有可能从成功变成退票状态）；
	 * FAIL：失败（具体失败原因请参见error_code以及fail_reason返回值）； 
	 * INIT：等待处理； 
	 * DEALING：处理中；
	 * REFUND：退票（仅配合"单笔转账到银行账户接口"产品使用时会涉及, 具体退票原因请参见fail_reason返回值）； 
	 * UNKNOWN：状态未知。
	 */
	@Override
	public AlipayFundTransOrderQueryResponse fundTransOrderQuery(String outBizNo) {
		AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
		AlipayFundTransOrderQueryModel model = new AlipayFundTransOrderQueryModel();
		model.setOutBizNo(outBizNo);
		request.setBizModel(model);
		try {
			AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
			if (!response.isSuccess()) {
				log.error("查询支付宝转账订单错误\n" + "订单号：" + outBizNo + "\n" + response.toString());
				throw new BusinessException(response.getMsg()+"\n"+response.getSubMsg());
			}
			return response;
		} catch (AlipayApiException e) {
			log.error("查询支付宝转账订单错误", e);
			throw new BusinessException("查询支付宝转账订单错误", e.getMessage());
		}
	}


}
