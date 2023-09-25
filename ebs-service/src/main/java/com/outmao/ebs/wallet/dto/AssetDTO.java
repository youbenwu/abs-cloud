package com.outmao.ebs.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 资产
 *
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetDTO {


	/**
	 * 钱包用户
	 */
	private Long userId;

	/**
	 * 资金钱包
	 */
	private Long walletId;

	/**
	 * 资产币种
	 */
	private String currencyId;



}
