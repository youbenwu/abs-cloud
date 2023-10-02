package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.SortEntity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "ebs_ProductTypeProperty",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "typeId", "_key" }) })
public class ProductTypeProperty  extends SortEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 * 商品参数挂到商品类型下
	 * 
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	private ProductType type;

	/**
	 *
	 * 属性KEY
	 *
	 */
	@Column(name = "_key")
	private String key;

	/**
	 * 
	 * 参数名称
	 * 
	 */
	private String name;
	

	/**
	 * 
	 * 参数值 一行代表一个可选值
	 * 
	 */
	private String value;


	/**
	 *
	 * 唯一属性  单选属性  复选属性
	 *
	 */
	private int propertyType;

	/**
	 *
	 * 相同参数值的商品是否关联
	 *
	 */
	private boolean assoc;

	/**
	 * 
	 * 能否进行检索 0--不需要检索 1--关键字检索 2--范围检索
	 * 
	 */
	private int searchType;


	/**
	 * 
	 * 参数录入方式：0--手动录入 1--列表中选择
	 * 
	 */
	private int inputType;

	/**
	 * 
	 * 是否支持手动新增
	 * 
	 */
	private boolean add;


}
