package com.outmao.ebs.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.Duration;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 资产交易
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_Trade")
public class Trade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 自动ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 交易发起方
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "fromId")
	private Wallet from;

	/**
	 * 交易对方
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
	 * 交易状态
	 */
	private int status;

	/**
	 * 状态备注
	 */
	private String statusRemark;

	/**
	 * 交易主题
	 */
	private String subject;

	/**
	 * 交易内容
	 */
	private String body;

	/**
	 *
	 * 交易类型
	 * 
	 */
	private int type;

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
	 * 交易单号
	 */
	@Column(unique = true)
	private String tradeNo;

	/**
	 * 支付渠道
	 */
	private int payChannel;

	/**
	 * 外部支付方式
	 */
	private int outPayType;

	/**
	 * 冻结期限
	 */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="field", column=@Column(name="freeze_field")),
			@AttributeOverride(name="value", column=@Column(name="freeze_value"))
	})
	private Duration freeze;

	/**
	 * 冻结到期时间
	 */
	private Date freezeExpireTime;

	/**
	 * 解冻时间
	 */
	private Date unfreezeTime;


	/**
	 * 冻结状态 0--未冻结 1--已冻结 2--已解冻
	 */
	private int freezeStatus;

	/**
	 * 冻结金额
	 */
	private long freezeAmount;

	/**
	 * 超时时间
	 */
	private Date timeoutTime;


	/**
	 * 支付成功后 完成交易超时时间
	 */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="field", column=@Column(name="finish_timeout_field")),
			@AttributeOverride(name="value", column=@Column(name="finish_timeout_value"))
	})
	private Duration finishTimeout;

	/**
	 * 支付成功后 完成交易超时时间
	 */
	private Date finishTimeoutTime;


	/**
	 * 交易金额
	 */
	private long amount;

	/**
	 * 手续费
	 */
	private long fee;

	/**
	 * 支出的总金额、包括交易金额和手续费
	 */
	private long totalAmount;

	/**
	 *
	 * 平台实收金额 第三方支付除去手续费
	 *
	 */
	private long receiptAmount;

	/**
	 *
	 * 付款方实际支付的金额
	 *
	 */
	private long payerAmount;

	/**
	 *
	 * 实际已给收款方的金额
	 *
	 */
	private long payeeAmount;
	
	/**
	 * 已退金额、不包括退的手续费
	 */
	private long refundAmount;

	/**
	 * 退款，是否原路退回，否则退回钱包
	 */
	private boolean refundOut;

	/**
	 * 交易的备注
	 */
	private String remark;

	/**
	 * 交易的开始时间
	 */
	private Date createTime;

	/**
	 * 支付成功时间
	 */
	private Date successTime;

	/**
	 * 交易完成时间
	 */
	private Date finishTime;

	/**
	 * 交易关闭时间
	 */
	private Date closeTime;




}
