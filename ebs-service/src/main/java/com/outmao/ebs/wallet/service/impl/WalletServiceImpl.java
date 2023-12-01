package com.outmao.ebs.wallet.service.impl;


import com.outmao.ebs.wallet.domain.*;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.*;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Order(0)
@Transactional
@Service
public class WalletServiceImpl implements WalletService , CommandLineRunner {

	@Autowired
	private CurrencyDomain currencyDomain;

	@Autowired
	private BankAccountDomain bankAccountDomain;

	@Autowired
    private WalletDomain walletDomain;

	@Autowired
    private AssetDomain assetDomain;

	@Autowired
	private TransferDomain transferDomain;

	@Override
	public void run(String... args) throws Exception {
		List<Currency> currencies=currencyDomain.getCurrencyList();
		if(currencies.isEmpty()){
			saveCurrency(new CurrencyDTO("RMB", "人民币", "元", 100, 100, true, new Fee(1,100,0)));
			saveCurrency(new CurrencyDTO("COIN", "金币", "个", 1, 1, false, null));
		}
	}


	@Override
	public Currency saveCurrency(CurrencyDTO params) {
		return currencyDomain.saveCurrency(params);
	}

	@Override
	public Currency getCurrencyById(String id) {
		return currencyDomain.getCurrencyById(id);
	}

	@Override
	public List<Currency> getCurrencyList() {
		return currencyDomain.getCurrencyList();
	}

	
	@Override
	public BankAccount saveBankAccount(BankAccountDTO request) {
		return bankAccountDomain.saveBankAccount(request);
	}

	@Override
	public void deleteBankAccountById(Long id) {
		bankAccountDomain.deleteBankAccountById(id);
	}

	@Override
	public BankAccount getBankAccountById(Long id) {
		return bankAccountDomain.getBankAccountById(id);
	}

	@Override
	public List<BankAccount> getBankAccountListByUserId(Long userId) {
		return bankAccountDomain.getBankAccountListByUserId(userId);
	}

	@Override
	public Page<BankAccount> getBankAccountPage(GetBankAccountListDTO request, Pageable pageable) {
		return bankAccountDomain.getBankAccountPage(request,pageable);
	}


	@Transactional
	@Override
	public Wallet registerWallet(RegisterWalletDTO request) {
		Wallet wallet= walletDomain.registerWallet(request);
		saveWalletAssetList(wallet);
		return wallet;
	}

	private void saveWalletAssetList(Wallet wallet){
		List<Currency> currencies=getCurrencyList();
		currencies.forEach(c->{
			AssetDTO assetDTO=new AssetDTO(wallet.getUser().getId(),wallet.getId(),c.getId());
			assetDomain.saveAsset(assetDTO);
		});
	}


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

		WalletVO vo= walletDomain.getWalletVOById(id);

		if(vo!=null){
			vo.setAssets(assetDomain.getAssetVOListByWalletId(vo.getId()));
		}

		return vo;
	}

	@Override
	public List<WalletVO> getWalletListVOByUserId(Long userId) {

		List<WalletVO> list= walletDomain.getWalletListVOByUserId(userId);

		setAssets(list);

		return list;
	}

	@Override
	public Page<WalletVO> getWalletVOPage(GetWalletListDTO request, Pageable pageable) {
		Page<WalletVO> page = walletDomain.getWalletVOPage(request,pageable);
		setAssets(page.getContent());
		return page;
	}

	private void setAssets(List<WalletVO> list){
		if(list.isEmpty())
			return;
		List<AssetVO> assets=assetDomain.getAssetVOListByWalletIdIn(list.stream().map(t->t.getId()).collect(Collectors.toList()));
		list.forEach(t->{
			t.setAssets(assets.stream().filter(a->a.getWalletId()==t.getId()).collect(Collectors.toList()));
		});
	}

	@Override
	public Page<AssetVO> getAssetVOPage(GetAssetListDTO request, Pageable pageable) {
		return assetDomain.getAssetVOPage(request,pageable);
	}

	@Override
	public Page<TransferVO> getTransferVOPage(GetTransferListDTO request, Pageable pageable) {
		return transferDomain.getTransferVOPage(request,pageable);
	}

	@Override
	public Page<SimpleTransferVO> getSimpleTransferVOPage(GetTransferListDTO request, Pageable pageable) {
		Page<TransferVO> page=getTransferVOPage(request,pageable);
		List<SimpleTransferVO> list=new ArrayList<>(page.getContent().size());
		for (TransferVO t:page.getContent()){
			SimpleTransferVO vo=new SimpleTransferVO();
			BeanUtils.copyProperties(t,vo);
			if(request.getWalletId().equals(t.getFromId())&&t.getFromType()== Transfer.TransferType.Balance){
				vo.setBalance(t.getFromBalance());
				vo.setType(0);
			}else{
				vo.setBalance(t.getToBalance());
				vo.setType(1);
			}
			list.add(vo);
		}
		Page<SimpleTransferVO> spage= new PageImpl(list,pageable,page.getTotalElements());
		return spage;
	}

	@Override
	public StatsTransferVO getStatsTransferVO(GetTransferListDTO request) {
		return transferDomain.getStatsTransferVO(request);
	}
}
