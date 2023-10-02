package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.SortEntity;
import lombok.Data;
import javax.persistence.*;

/**
 *
 * 参数分组
 *
 */
@Data
@Entity
@Table(name = "ebs_ProductTypeAttributeGroup",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "typeId", "_key" }) })
public class ProductTypeAttributeGroup extends SortEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 * 商品类型
	 * 
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	private ProductType type;

	/**
	 *
	 * 分组KEY
	 *
	 */
	@Column(name = "_key")
	private String key;

	/**
	 * 
	 * 分组名称
	 * 
	 */
	private String name;




}
