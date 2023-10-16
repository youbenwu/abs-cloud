package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.SortEntity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "ebs_ProductTypeAttribute",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "typeId", "_key" }) })
public class ProductTypeAttribute extends SortEntity {

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
	 * 分组
	 *
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId")
	private ProductTypeAttributeGroup group;

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
	 * 显示后缀
	 *
	 */
	private String suffix;

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
	 * 参数录入方式：0--手动录入 1--单选参数 2--多选参数
	 * 
	 */
	private int inputType;


}
