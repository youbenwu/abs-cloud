package com.outmao.ebs.wallet.web.api;


import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "wallet-currency", tags = "钱包-币种")
@RestController
@RequestMapping("/api/wallet/currency")
public class CurrencyAction {

	@Autowired
	private CurrencyService currencyService;

	//Currency

	/*
	 *
	 * 获取币种信息
	 *
	 * */
	@ApiOperation(value = "获取币种信息", notes = "获取币种信息")
	@PostMapping("/get")
	public Currency getCurrencyById(String id){
		return currencyService.getCurrencyById(id);
	}

	/*
	 *
	 * 获取所有币种信息
	 *
	 * */
	@ApiOperation(value = "获取币种信息", notes = "获取币种信息")
	@PostMapping("/list")
	public List<Currency> getCurrencyList(){
		return currencyService.getCurrencyList();
	}


}
