package com.outmao.ebs.mall.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.Address;
import com.outmao.ebs.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 发货地址，收货地址
 *
 */
@Data
@Entity
@Table(name = "ebs_ShopAddress")
public class ShopAddress extends Address implements Serializable {

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
	@JoinColumn(name = "shopId",updatable = false)
	private Shop shop;

	/**
	 *
	 * 0--发货地址 1--退货地址
	 *
	 */
	private int type;


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
