package com.outmao.ebs.wallet.web.admin.api;



import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.AssetVO;
import com.outmao.ebs.wallet.vo.TransferVO;
import com.outmao.ebs.wallet.vo.WalletVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "admin-wallet", tags = "后台-钱包")
@RestController
@RequestMapping("/api/admin/wallet")
public class WalletAdminAction {

	@Autowired
    private WalletService walletService;


	@PreAuthorize("hasPermission('wallet','save')")
	@ApiOperation(value = "保存钱包信息", notes = "保存钱包信息")
	@PostMapping("/save")
	public void saveWallet(WalletDTO request) {
		 walletService.saveWallet(request);
	}

	@PreAuthorize("hasPermission('wallet','save')")
	@ApiOperation(value = "设置钱包密码", notes = "设置钱包密码")
	@PostMapping("/setPassword")
	public void setWalletPassword(SetWalletPasswordDTO request) {
		 walletService.setWalletPassword(request);
	}

	@PreAuthorize("hasPermission('wallet','status')")
	@ApiOperation(value = "设置钱包状态", notes = "设置钱包状态")
	@PostMapping("/setStatus")
	public void setWalletStatus(SetWalletStatusDTO request) {
		 walletService.setWalletStatus(request);
	}

	@PreAuthorize("hasPermission('wallet','read')")
	@ApiOperation(value = "获取钱包信息", notes = "获取钱包信息")
	@PostMapping("/get")
	public WalletVO getWalletVOById(Long id) {
		return walletService.getWalletVOById(id);
	}

	@PreAuthorize("hasPermission('wallet','read')")
	@ApiOperation(value = "获取钱包信息列表", notes = "获取钱包信息列表")
	@PostMapping("/list")
	public List<WalletVO> getWalletListVOByUserId(Long userId) {
		return walletService.getWalletListVOByUserId(userId);
	}

	@PreAuthorize("hasPermission('wallet','read')")
	@ApiOperation(value = "获取钱包信息列表", notes = "获取钱包信息列表")
	@PostMapping("/page")
	public Page<WalletVO> getWalletVOPage(GetWalletListDTO request, Pageable pageable) {
		return walletService.getWalletVOPage(request,pageable);
	}

	@PreAuthorize("hasPermission('wallet','read')")
	@ApiOperation(value = "获取资产列表", notes = "获取资产列表")
	@PostMapping("/asset/page")
	public Page<AssetVO> getAssetVOPage(GetAssetListDTO request, Pageable pageable) {
		return walletService.getAssetVOPage(request,pageable);
	}

	@PreAuthorize("hasPermission('wallet','read')")
	@ApiOperation(value = "获取交易明细列表", notes = "获取交易明细列表")
	@PostMapping("/transfer/page")
	public Page<TransferVO> getTransferVOPage(GetTransferListDTO request, Pageable pageable) {
		return walletService.getTransferVOPage(request,pageable);
	}


}
