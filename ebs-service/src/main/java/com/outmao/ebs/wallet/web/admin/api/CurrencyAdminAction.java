package com.outmao.ebs.wallet.web.admin.api;


import com.outmao.ebs.wallet.dto.CurrencyDTO;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "admin-wallet-currency", tags = "后台-钱包-币种")
@RestController
@RequestMapping("/api/admin/wallet/currency")
public class CurrencyAdminAction {

	@Autowired
	private CurrencyService currencyService;

	//Currency

	/*
	 *
	 * 保存币种信息
	 *
	 * */
	@PreAuthorize("hasPermission('wallet/currency','save')")
	@ApiOperation(value = "保存币种信息", notes = "保存币种信息")
	@PostMapping("/save")
	public Currency saveCurrency(CurrencyDTO params){
		return currencyService.saveCurrency(params);
	}

	/*
	 *
	 * 获取币种信息
	 *
	 * */
	@PreAuthorize("hasPermission('wallet/currency','read')")
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
	@PreAuthorize("hasPermission('wallet/currency','read')")
	@ApiOperation(value = "获取币种信息", notes = "获取币种信息")
	@PostMapping("/list")
	public List<Currency> getCurrencyList(){
		return currencyService.getCurrencyList();
	}


}
