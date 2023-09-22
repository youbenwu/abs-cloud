package com.outmao.ebs.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户信息
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@JsonInclude(value = Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_User")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 用户标识
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 *
	 * 搜索关键字
	 *
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String keyword;

	/**
	 *
	 * 钱包ID
	 *
	 */
	@Column(unique = true)
	private Long walletId;

	/**
	 * 状态0正常1禁用
	 */
	private int status;

	/**
	 * 用户类型 0--普通用户 1--机器人
	 */
	private int type;


	@JsonIgnore
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "detailsId")
	private UserDetails details;

	/**
	 *
	 * 用户帐号
	 *
	 */
	@Column(unique = true)
	private String username;

	/**
	 *
	 * 用户密码
	 *
	 */
	@JsonIgnore
	private String password;

	/**
	 *
	 * 用户昵称
	 *
	 */
	private String nickname;

	/**
	 * 
	 * 用户头像
	 *
	 */
	private String avatar;

	/**
	 *
	 * 注册所在城市
	 *
	 */
	private String area;

	/**
	 *
	 * 注册所在城市编码
	 *
	 */
	private String areaCode;

	/**
	 *
	 * 用户设备号
	 *
	 */
	private String imei;

	/**
	 * 
	 * 实名认证状态
	 * 
	 */
	private boolean certified;

	/**
	 * 积分
	 */
	private long credits;

	/**
	 * 等级
	 */
	private int level;

	/**
	 * vip等级
	 */
	private int vip;

	/**
	 *
	 * 个人主页H5地址
	 *
	 */
	private String url;

	/**
	 *
	 * 个人主页二维码
	 *
	 */
	private String qrCode;

	/**
	 *
	 * 是否在线
	 *
	 */
	private boolean online;

	/**
	 *
	 * 注册时间
	 *
	 */
	private Date registerTime;

	/**
	 *
	 * 最近登录时间
	 *
	 */
	private Date loginTime;


	public int hashCode() {
		return (this.getId() == null) ? 0 : this.getId().hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof User) {
			final User obj = (User) object;
			return (this.getId() != null) ? this.getId().equals(obj.getId()) : (obj.getId() == null);
		}
		return false;
	}


}
