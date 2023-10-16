package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * 商品属性选项
 * 
 */
@Data
@Entity
@Table(name = "ebs_ProductPropertyItem")
public class ProductPropertyItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
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

	@JsonIgnore
	@Column(nullable = false,updatable = false)
	private Long propertyId;

	/**
	 *
	 * 属性KEY
	 *
	 */
	@JsonIgnore
	@Column(name = "_key")
	private String key;

	/**
	 * 
	 * 属性的值
	 * 
	 */
	@Column(nullable = false)
	private String value;


}
