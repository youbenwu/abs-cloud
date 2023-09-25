package com.outmao.ebs.wallet.service;


import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.BankAccount;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.vo.AssetVO;
import com.outmao.ebs.wallet.vo.TransferVO;
import com.outmao.ebs.wallet.vo.WalletVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface WalletService {


	/**
	 *
	 * 保存货币信息
	 *
	 */
	public Currency saveCurrency(CurrencyDTO params);

	/**
	 *
	 * 获取货币信息
	 *
	 */
	public Currency getCurrencyById(String id);

	/**
	 *
	 * 获取货币信息
	 *
	 */
	public List<Currency> getCurrencyList();



	public BankAccount saveBankAccount(BankAccountDTO request);

	public void deleteBankAccountById(Long id);

	public BankAccount getBankAccountById(Long id);

	public List<BankAccount> getBankAccountListByUserId(Long userId);

	public Page<BankAccount> getBankAccountPage(GetBankAccountListDTO request, Pageable pageable);

	/**
	 *
	 * 注册用户钱包
	 *
	 */
	public Wallet registerWallet(RegisterWalletDTO request);

	/**
	 *
	 * 修改钱包资料
	 *
	 */
	public Wallet saveWallet(WalletDTO request);


	/**
	 *
	 * 设置钱包密码
	 *
	 */
	public Wallet setWalletPassword(SetWalletPasswordDTO request);


	/**
	 *
	 * 修改钱包状态
	 *
	 */
	public Wallet setWalletStatus(SetWalletStatusDTO request);


	/**
	 *
	 * 获取钱包
	 *
	 *
	 * */
	public WalletVO getWalletVOById(Long id);


	/**
	 *
	 * 获取用户钱包
	 *
	 */
	public List<WalletVO> getWalletListVOByUserId(Long userId);


	/**
	 *
	 * 获取钱包列表
	 *
	 */
	public Page<WalletVO> getWalletVOPage(GetWalletListDTO request, Pageable pageable);


	/**
	 *
	 * 获取资产列表
	 *
	 */
	public Page<AssetVO> getAssetVOPage(GetAssetListDTO request, Pageable pageable);

	/**
	 *
	 * 获取交易明细
	 *
	 */
	public Page<TransferVO> getTransferVOPage(GetTransferListDTO request, Pageable pageable);




}
