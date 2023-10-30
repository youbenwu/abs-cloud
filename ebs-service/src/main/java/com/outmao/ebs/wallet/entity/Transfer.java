package com.outmao.ebs.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 钱包转帐 一次交易会产生多次转帐行为
 * 
 * 
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_Transfer")
public class Transfer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TransferType {
		/**
		 *
		 * 转帐类型
		 * 
		 */
		Balance, // 转佘额
		Frozen, // 转冻结
		Advance,// 转预付款
	}

	/**
	 * 自动ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	/**
	 * 所属交易
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "tradeId")
	private Trade trade;

	/**
	 * 贷方钱包
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "fromId")
	private Wallet from;

	/**
	 * 借方钱包
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "toId")
	private Wallet to;

	/**
	 * 交易的币种
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "currencyId")
	private Currency currency;


	/**
	 *
	 * 转帐类型
	 *
	 */
	private TransferType fromType;

	/**
	 *
	 * 转帐类型
	 *
	 */
	private TransferType toType;


	/**
	 * 
	 * 转帐金额
	 * 
	 */
	private long amount;

	/**
	 *
	 * 转出后佘额
	 *
	 */
	private long fromBalance;

	/**
	 *
	 * 转入后佘额
	 *
	 */
	private long toBalance;

	/**
	 * 
	 * 自定义业务类型
	 * 
	 */
	private int businessType;

	/**
	 * 
	 * 自定义业务说明
	 * 
	 */
	private String business;

	/**
	 *
	 * 转帐单号
	 * 
	 */
	private String transferNo;

	/**
	 * 操作KEY，用户每次操作产生一个KEY
	 */
	private String actionKey;

	/**
	 * 转帐备注
	 * 
	 */
	private String remark;

	/**
	 * 转帐发生时间
	 * 
	 */
	private Date createTime;



}
