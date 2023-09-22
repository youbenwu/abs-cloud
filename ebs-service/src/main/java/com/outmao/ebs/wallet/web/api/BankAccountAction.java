package com.outmao.ebs.wallet.web.api;


import com.outmao.ebs.wallet.dto.BankAccountDTO;
import com.outmao.ebs.wallet.entity.BankAccount;
import com.outmao.ebs.wallet.service.BankAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "wallet-bankAccount", tags = "钱包-银行账户")
@RestController
@RequestMapping("/api/wallet/bankAccount")
public class BankAccountAction {

	@Autowired
	private BankAccountService bankAccountService;


	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存银行账户信息", notes = "保存银行账户信息")
	@PostMapping("/save")
	public BankAccount saveBankAccount(BankAccountDTO request) {
		return bankAccountService.saveBankAccount(request);
	}


	@ApiOperation(value = "删除银行账户信息", notes = "删除银行账户信息")
	@PostMapping("/delete")
	public void deleteBankAccountById(Long id) {
		bankAccountService.deleteBankAccountById(id);
	}

	@PostAuthorize("principal.id.equals(#returnObject.user.id)")
	@ApiOperation(value = "获取银行账户信息", notes = "获取银行账户信息")
	@PostMapping("/get")
	public BankAccount getBankAccountById(Long id) {
		return bankAccountService.getBankAccountById(id);
	}


	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取银行账户信息列表", notes = "获取银行账户信息列表")
	@PostMapping("/list")
	public List<BankAccount> getBankAccountListByUserId(Long userId) {
		return bankAccountService.getBankAccountListByUserId(userId);
	}


}
