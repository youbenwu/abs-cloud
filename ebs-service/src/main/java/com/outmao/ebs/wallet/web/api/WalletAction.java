package com.outmao.ebs.wallet.web.api;


import com.outmao.ebs.wallet.dto.BankAccountDTO;
import com.outmao.ebs.wallet.dto.GetTransferListDTO;
import com.outmao.ebs.wallet.dto.SetWalletPasswordDTO;
import com.outmao.ebs.wallet.dto.WalletDTO;
import com.outmao.ebs.wallet.entity.BankAccount;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.SimpleTransferVO;
import com.outmao.ebs.wallet.vo.StatsTransferVO;
import com.outmao.ebs.wallet.vo.TransferVO;
import com.outmao.ebs.wallet.vo.WalletVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(value = "wallet", tags = "钱包")
@RestController
@RequestMapping("/api/wallet")
public class WalletAction {

	@Autowired
    private WalletService walletService;


	@ApiOperation(value = "获取币种信息", notes = "获取币种信息")
	@PostMapping("/currency/get")
	public Currency getCurrencyById(String id){
		return walletService.getCurrencyById(id);
	}

	@ApiOperation(value = "获取所有币种信息", notes = "获取所有币种信息")
	@PostMapping("/currency/list")
	public List<Currency> getCurrencyList(){
		return walletService.getCurrencyList();
	}


	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存银行账户信息", notes = "保存银行账户信息")
	@PostMapping("/bankAccount/save")
	public BankAccount saveBankAccount(BankAccountDTO request) {
		return walletService.saveBankAccount(request);
	}


	@ApiOperation(value = "删除银行账户信息", notes = "删除银行账户信息")
	@PostMapping("/bankAccount/delete")
	public void deleteBankAccountById(Long id) {
		walletService.deleteBankAccountById(id);
	}

	@PostAuthorize("principal.id.equals(returnObject.user.id)")
	@ApiOperation(value = "获取银行账户信息", notes = "获取银行账户信息")
	@PostMapping("/bankAccount/get")
	public BankAccount getBankAccountById(Long id) {
		return walletService.getBankAccountById(id);
	}


	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取银行账户信息列表", notes = "获取银行账户信息列表")
	@PostMapping("/bankAccount/list")
	public List<BankAccount> getBankAccountListByUserId(Long userId) {
		return walletService.getBankAccountListByUserId(userId);
	}


	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存钱包信息", notes = "保存钱包信息")
	@PostMapping("/save")
	public Wallet saveWallet(WalletDTO request) {
		 return walletService.saveWallet(request);
	}

	@PostAuthorize("principal.id.equals(returnObject.user.id)")
	@ApiOperation(value = "设置钱包密码", notes = "设置钱包密码")
	@PostMapping("/setPassword")
	public Wallet setWalletPassword(SetWalletPasswordDTO request) {
		 return walletService.setWalletPassword(request);
	}


	@PostAuthorize("principal.id.equals(returnObject.userId)")
	@ApiOperation(value = "获取钱包信息", notes = "获取钱包信息")
	@PostMapping("/get")
	public WalletVO getWalletVOById(Long id) {
		return walletService.getWalletVOById(id);
	}

	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取钱包信息列表", notes = "获取钱包信息列表")
	@PostMapping("/list")
	public List<WalletVO> getWalletListVOByUserId(Long userId) {
		return walletService.getWalletListVOByUserId(userId);
	}


	@ApiOperation(value = "获取交易明细列表", notes = "获取交易明细列表")
	@PostMapping("/transfer/page")
	public Page<TransferVO> getTransferVOPage(GetTransferListDTO request, Pageable pageable) {
		return walletService.getTransferVOPage(request,pageable);
	}

	@ApiOperation(value = "获取交易明细列表", notes = "获取交易明细列表")
	@PostMapping("/transfer/simple/page")
	public Page<SimpleTransferVO> getSimpleTransferVOPage(GetTransferListDTO request, Pageable pageable){
		return walletService.getSimpleTransferVOPage(request,pageable);
	}


	@ApiOperation(value = "获取交易明细统计", notes = "获取交易明细统计")
	@PostMapping("/transfer/stats")
	public StatsTransferVO getStatsTransferVO(GetTransferListDTO request) {
		return walletService.getStatsTransferVO(request);
	}




}
