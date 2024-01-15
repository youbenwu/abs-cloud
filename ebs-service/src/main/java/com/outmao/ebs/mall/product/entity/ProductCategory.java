package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "ebs_ProductCategory")
public class ProductCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分类唯一不变标识
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 *
	 * 唯一编码
	 *
	 */
	@Column(unique = true)
	private String code;

	/**
	 *
	 * 商品类型
	 *
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	private ProductType type;

	/**
	 * 分类的父分类
	 * 
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private ProductCategory parent;

	/**
	 * 分类的子分类
	 * 
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@OrderBy(value = "sort ASC")
	private List<ProductCategory> children;

	/**
	 * 多级分类中所处的级别，级别从0开始
	 * 
	 */
	private int level;

	/**
	 * 多级分类中是否是叶子节点的标识
	 * 
	 */
	private boolean leaf;

	/**
	 * 排序序号，从0形始
	 * 
	 */
	private int sort;

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
	 * 分类图片,分类的图片地址
	 *
	 */
	private String image;

	/**
	 *
	 * 分类的标题
	 * 
	 */
	@Column(nullable = false)
	private String title;

	/**
	 * 分类的描述
	 */
	private String description;

	/**
	 * 
	 * 拼音字母,分类标题的拼音字母，用于检索
	 * 
	 */
	private String letter;

	/**
	 *
	 * 商品类型
	 *
	 */
	private int productType;

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

	/**
	 * 
	 * 创建时间
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 * 更新时间
	 * 
	 */
	private Date updateTime;


}
