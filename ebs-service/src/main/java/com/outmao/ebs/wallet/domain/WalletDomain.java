package com.outmao.ebs.wallet.domain;


import com.outmao.ebs.wallet.dto.GetWalletListDTO;
import com.outmao.ebs.wallet.dto.SetWalletPasswordDTO;
import com.outmao.ebs.wallet.dto.SetWalletStatusDTO;
import com.outmao.ebs.wallet.dto.WalletDTO;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.vo.WalletVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WalletDomain {


	/**
	 * 
	 * 创建用户钱包
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





	
}
