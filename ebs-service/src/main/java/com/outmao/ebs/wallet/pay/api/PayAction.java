package com.outmao.ebs.wallet.pay.api;


import com.outmao.ebs.common.vo.Result;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.dto.PayPrepareDTO;
import com.outmao.ebs.wallet.pay.dto.PayWalletDTO;
import com.outmao.ebs.wallet.pay.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "wallet-pay", tags = "钱包-支付")
@RestController
@RequestMapping("/api/wallet/pay")
public class PayAction {

	@Autowired
    private PayService payService;

	/*
	 *
	 * 获取APP支付订单信息 直接给客户端请求，无需再做处理
	 *
	 * */
	@ApiOperation(value = "APP支付", notes = "获取APP支付订单信息 直接给客户端请求，无需再做处理")
	@RequestMapping(value = "/prepare", method = RequestMethod.POST)
	public Object tradePrepare(PayPrepareDTO request){
		return Result.successResult(payService.payPrepare(request));
	}



	//钱包支付
	@ApiOperation(value = "钱包支付", notes = "钱包支付")
	@RequestMapping(value = "/walletPay", method = RequestMethod.POST)
	public Trade tradeWalletPay(@RequestBody PayWalletDTO request){
		return payService.payWallet(request);
	}

	//交易查询
	@ApiOperation(value = "交易查询", notes = "交易查询")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Trade tradeQuery(String tradeNo){
		return payService.tradeQuery(tradeNo);
	}



}
