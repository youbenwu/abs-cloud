package com.outmao.ebs.mall.product.entity;


import com.outmao.ebs.common.vo.SortEntity;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
	 * 商品类型
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

	/**
	 * 
	 * 属性个数
	 * 
	 */
	private int propertyCount;

	/**
	 * 
	 * 参数个数
	 * 
	 */
	private int attributeCount;



}
