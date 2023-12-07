package com.outmao.ebs.mall.product.entity;


import com.outmao.ebs.common.vo.SortEntity;
import lombok.Data;

import javax.persistence.*;


/**
 * 
 * 商品类型
 * 
 */
@Data
@Entity
@Table(name = "ebs_ProductType")
public class ProductType extends SortEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 *
	 * 搜索关键字
	 *
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String keyword;


	/**
	 *
	 * 商品业务类型
	 *
	 */
	private int type;


	/**
	 * 
	 * 类型名称
	 * 
	 */
	@Column(unique = true)
	private String name;

	/**
	 *
	 * 类型描述
	 *
	 */
	private String description;


	/**
	 *
	 * 是否无需发货
	 *
	 */
	private boolean noDelivery;


	/**
	 *
	 * 租赁信息
	 *
	 */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="lease", column=@Column(name="is_lease")),
			@AttributeOverride(name="field", column=@Column(name="lease_field")),
			@AttributeOverride(name="min", column=@Column(name="lease_min"))
	})
	private ProductLease lease;

	/**
	 *
	 * 是否允许商家标记签收
	 *
	 */
	private boolean sellerFinish;


}
