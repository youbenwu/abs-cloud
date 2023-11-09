package com.outmao.ebs.mall.product.entity;


import com.outmao.ebs.common.vo.SortEntity;
import lombok.Data;

import javax.persistence.*;


/**
 * 
 * 商品类型
 * 
 */
@Data
@Entity
@Table(name = "ebs_ProductType")
public class ProductType extends SortEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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
	 * 商品业务类型
	 *
	 */
	private int type;


	/**
	 * 
	 * 类型名称
	 * 
	 */
	@Column(unique = true)
	private String name;

	/**
	 *
	 * 类型描述
	 *
	 */
	private String description;





}
