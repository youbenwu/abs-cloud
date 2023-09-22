package com.outmao.ebs.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * 用户授权
 *
 */
@Data
@Entity
@Table(name = "ebs_UserOauth")
public class UserOauth implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 自动ID
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 *
	 * 授权用户
	 *
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	/**
	 *
	 * 授权类型
	 *
	 */
	private String oauth;

	/**
	 *
	 * 用户标识
	 *
	 */
	@Column(unique = true)
	private String principal;

	/**
	 *
	 * 凭证
	 *
	 */
	private String credentials;

	/**
	 *
	 * 注册时间
	 *
	 */
	private Date registerTime;


	public int hashCode() {
		return (this.id == null) ? 0 : this.id.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof UserOauth) {
			final UserOauth obj = (UserOauth) object;
			return Objects.equals(this.id,obj.id);
		}
		return false;
	}


}
