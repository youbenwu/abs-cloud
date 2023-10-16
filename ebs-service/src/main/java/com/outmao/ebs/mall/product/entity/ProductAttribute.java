package com.outmao.ebs.mall.product.entity;

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
	 * 显示后缀
	 *
	 */
	private String suffix;



}
