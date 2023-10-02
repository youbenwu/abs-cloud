package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

//	/**
//	 *
//	 * 商品规格属性选项
//	 *
//	 */
//	@JsonIgnore
//	@OneToMany(mappedBy = "property", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@OrderBy(items = "id ASC")
//	private List<ProductPropertyItem> items;

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

//	/**
//	 *
//	 *
//	 * 属性类型 唯一属性  单选属性  复选属性
//	 *
//	 *
//	 */
//	private int type;

	/**
	 *
	 * 相同参数值的商品是否关联
	 *
	 */
	@JsonIgnore
	private boolean linked;


	/**
	 *
	 * 能否进行检索 0--不需要检索 1--关键字检索 2--范围检索
	 *
	 */
	@JsonIgnore
	private int searchType;


//	public ProductPropertyItem getItem(String items){
//		for (ProductPropertyItem item:items){
//			if(item.getItems().equals(items))
//				return item;
//		}
//		return null;
//	}


}
