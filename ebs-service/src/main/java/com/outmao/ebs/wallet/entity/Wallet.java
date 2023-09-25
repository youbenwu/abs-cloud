package com.outmao.ebs.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 
 * 钱包
 * 
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_Wallet", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "type" }) })
public class Wallet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 钱包ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 钱包用户
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/**
	 * 
	 * 钱包类型 0--个人钱包 1--商户钱包
	 * 
	 */
	private int type;

	/**
	 * 钱包状态 0--正常 1--冻结
	 */
	private int status;

	/**
	 * 钱包状态备注
	 */
	private String statusRemark;

	/**
	 *
	 * 绑定的银行帐户
	 *
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "bankAccountId")
	private BankAccount bankAccount;

	/**
	 *
	 * 钱包户名
	 *
	 */
	private String realName;

	/**
	 *
	 * 绑定手机号
	 *
	 */
	private String phone;

	/**
	 * 钱包帐号
	 */
	@Column(unique = true)
	private String account;


	/**
	 * 钱包密码
	 */
	private String password;


	/**
	 * 钱包资产
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "wallet", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Asset> assets;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;


	public int hashCode() {
		return (this.id == null) ? 0 : this.id.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof Wallet) {
			final Wallet obj = (Wallet) object;
			return Objects.equals(this.id,obj.id);
		}
		return false;
	}


}
