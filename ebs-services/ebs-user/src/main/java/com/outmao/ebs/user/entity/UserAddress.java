package com.outmao.ebs.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.Address;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户地址信息
 *
 */
@Data
@Entity
@Table(name = "ebs_UserAddress")
public class UserAddress extends Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 所属用户
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/**
	 *
	 * 是否默认
	 *
	 */
	private boolean def;

	/**
	 *
	 * 联系人
	 *
	 */
	private String name;

	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 联系电话2
	 */
	private String phone2;

	/**
	 *
	 * 地址标签
	 *
	 */
	private String mark;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;



}
