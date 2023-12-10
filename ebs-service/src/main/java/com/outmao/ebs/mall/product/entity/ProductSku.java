package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * SKU
 * 
 */
@Data
@Entity
@Table(name = "ebs_ProductSku",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "productId", "_key" }) })
public class ProductSku implements Serializable {

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
	 * 属性所在的商品
	 * 
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "productId",updatable = false)
	private Product product;

	private int sort;

	/**
	 *
	 * SKU名称
	 *
	 */
	private String name;

	/**
	 *
	 * SKU标签
	 *
	 */
	private String marks;

	/**
	 * 
	 * SKU编号
	 * 
	 */
	private String skuNo;

	/**
	 *
	 * 商品SKU图片
	 * 
	 */
	private String image;

	/**
	 * 
	 * 用规格ProductPropertyItem ID生成，id1,id2..
	 * 
	 */
	@Column(name = "_key")
	private String key;

	/**
	 *
	 * 规格值 JSON [{key,name,items:{id,value}},]
	 * 
	 */
	@Lob
	private String value;


	/**
	 * 商品价格
	 */
	private double price;

	/**
	 *
	 * 单价（房屋销售里的每平方米单价）
	 *
	 */
	private Double unitPrice;

	/**
	 * 商品库存
	 */
	private long stock;

	/**
	 * 告警库存
	 */
	private Long alarmStock;



}
