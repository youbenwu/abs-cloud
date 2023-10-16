package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * 商品属性
 * 
 */
@Data
@Entity
@Table(name = "ebs_ProductProperty")
public class ProductProperty implements Serializable {

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
	@JsonIgnore
	@Column(nullable = false,updatable = false)
	private Long productId;

	/**
	 * 排序
	 */
	@JsonIgnore
	private int sort;


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
	@Column(nullable = false)
	private String name;

	/**
	 * 
	 * 属性的值 List<ProductPropertyItem> JSON
	 * 
	 */
	private String value;

	/**
	 *
	 * 显示后缀
	 *
	 */
	private String suffix;

	/**
	 *
	 *
	 * 属性类型 单选属性  复选属性  唯一属性
	 *
	 *
	 */
	private int type;



}
