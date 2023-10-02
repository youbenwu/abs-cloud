package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * 商品参数
 * 
 */
@Data
@Entity
@Table(name = "ebs_ProductAttribute")
public class ProductAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一不变标识
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 *
	 * 属性所在的商品
	 * 
	 */
	private Long productId;

	/**
	 *
	 * 属性所在的分组
	 *
	 */
	private Long groupId;

	/**
	 *
	 * 属性KEY
	 *
	 */
	@Column(name = "_key")
	private String key;

	/**
	 *
	 * 属性名称
	 * 
	 */
	private String name;

	/**
	 * 
	 * 属性的值
	 * 
	 */
	private String value;

	/**
	 *
	 * 用于计算的值
	 *
	 */
	private String v;

	/**
	 *
	 * 相同参数值的商品是否关联
	 *
	 */
	private boolean linked;


	/**
	 *
	 * 能否进行检索 0--不需要检索 1--关键字检索 2--范围检索
	 *
	 */
	private int searchType;



}
