package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * 商品参数分组
 * 
 */
@Data
@Entity
@Table(name = "ebs_ProductAttributeGroup")
public class ProductAttributeGroup implements Serializable {

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



}
