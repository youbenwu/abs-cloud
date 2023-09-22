package com.outmao.ebs.wallet.web.admin.api;


import com.outmao.ebs.wallet.dto.BankAccountDTO;
import com.outmao.ebs.wallet.dto.GetBankAccountListDTO;
import com.outmao.ebs.wallet.entity.BankAccount;
import com.outmao.ebs.wallet.service.BankAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-wallet-bankAccount", tags = "后台-钱包-银行账户")
@RestController
@RequestMapping("/api/admin/wallet/bankAccount")
public class BankAccountAdminAction {

	@Autowired
	private BankAccountService bankAccountService;


	@PreAuthorize("hasPermission('wallet/bankAccount','save')")
	@ApiOperation(value = "保存银行账户信息", notes = "保存银行账户信息")
	@PostMapping("/save")
	public BankAccount saveBankAccount(BankAccountDTO request) {
		return bankAccountService.saveBankAccount(request);
	}

	@PreAuthorize("hasPermission('wallet/bankAccount','delete')")
	@ApiOperation(value = "删除银行账户信息", notes = "删除银行账户信息")
	@PostMapping("/delete")
	public void deleteBankAccountById(Long id) {
		bankAccountService.deleteBankAccountById(id);
	}

	@PreAuthorize("hasPermission('wallet/bankAccount','read')")
	@ApiOperation(value = "获取银行账户信息", notes = "获取银行账户信息")
	@PostMapping("/get")
	public BankAccount getBankAccountById(Long id) {
		return bankAccountService.getBankAccountById(id);
	}


	@PreAuthorize("hasPermission('wallet/bankAccount','read')")
	@ApiOperation(value = "获取银行账户信息列表", notes = "获取银行账户信息列表")
	@PostMapping("/page")
	public Page<BankAccount> getBankAccountPage(GetBankAccountListDTO request, Pageable pageable) {
		return bankAccountService.getBankAccountPage(request,pageable);
	}


}
