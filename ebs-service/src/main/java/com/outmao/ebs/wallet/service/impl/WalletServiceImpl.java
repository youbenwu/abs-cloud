package com.outmao.ebs.wallet.service.impl;


import com.outmao.ebs.wallet.domain.AssetDomain;
import com.outmao.ebs.wallet.domain.TransferDomain;
import com.outmao.ebs.wallet.domain.WalletDomain;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.AssetVO;
import com.outmao.ebs.wallet.vo.TransferVO;
import com.outmao.ebs.wallet.vo.WalletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
    private WalletDomain walletDomain;

	@Autowired
    private AssetDomain assetDomain;

	@Autowired
	private TransferDomain transferDomain;


	@Override
	public Wallet saveWallet(WalletDTO request) {
		return walletDomain.saveWallet(request);
	}

	@Override
	public Wallet setWalletPassword(SetWalletPasswordDTO request) {
		return walletDomain.setWalletPassword(request);
	}

	@Override
	public Wallet setWalletStatus(SetWalletStatusDTO request) {
		return walletDomain.setWalletStatus(request);
	}

	@Override
	public WalletVO getWalletVOById(Long id) {
		return walletDomain.getWalletVOById(id);
	}

	@Override
	public List<WalletVO> getWalletListVOByUserId(Long userId) {
		return walletDomain.getWalletListVOByUserId(userId);
	}

	@Override
	public Page<WalletVO> getWalletVOPage(GetWalletListDTO request, Pageable pageable) {
		return walletDomain.getWalletVOPage(request,pageable);
	}

	@Override
	public Page<AssetVO> getAssetVOPage(GetAssetListDTO request, Pageable pageable) {
		return assetDomain.getAssetVOPage(request,pageable);
	}

	@Override
	public Page<TransferVO> getTransferVOPage(GetTransferListDTO request, Pageable pageable) {
		return transferDomain.getTransferVOPage(request,pageable);
	}


}
