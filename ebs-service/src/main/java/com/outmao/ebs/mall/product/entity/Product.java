package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.bbs.common.data.BindingSubjectId;
import com.outmao.ebs.common.vo.Location;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 产品实体
 * 
 */
@Data
@Entity
@Table(name = "ebs_Product")
public class Product implements Serializable, BindingSubjectId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 商品ID
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 *
	 * 绑定主题ID
	 *
	 */
	private Long subjectId;

	/**
	 *
	 * 所属组织ID
	 *
	 */
	@Column(nullable = false,updatable = false)
	private Long orgId;

	/**
	 *
	 * 所属用户ID
	 *
	 */
	@Column(nullable = false,updatable = false)
	private Long userId;


	/**
	 *
	 * 所属商家ID
	 *
	 */
	@Column(nullable = false,updatable = false)
	private Long merchantId;

	/**
	 *
	 * 商品店铺ID
	 * 
	 */
	@Column(nullable = false,updatable = false)
	private Long shopId;

	/**
	 *
	 * 酒店ID，酒店中商品
	 *
	 */
	private Long hotelId;


	/**
	 *
	 * 商品分类ID
	 * 
	 */
	@Column(nullable = false)
	private Long categoryId;

	/**
	 *
	 *
	 * 店铺中的商品分类ID
	 *
	 *
	 */
	private Long spcId;

	/**
	 *
	 * 商品品牌ID
	 * 
	 */
	private Long brandId;


	/**
	 *
	 * 顾问ID
	 *
	 */
	private Long counselorId;


//	/**
//	 *
//	 * 商品属性
//	 *
//	 */
//	@JsonIgnore
//	@OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@OrderBy(items = "id ASC")
//	private List<ProductAttributeGroup> attributes;

//	/**
//	 *
//	 * 商品规格属性
//	 *
//	 */
//	@JsonIgnore
//	@OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@OrderBy(items = "id ASC")
//	private List<ProductProperty> properties;

	/**
	 *
	 * 商品SKUS属性
	 *
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@OrderBy(value = "id ASC")
	private List<ProductSku> skus;
//
//	/**
//	 *
//	 * 商品轮播图片
//	 *
//	 */
//	@JsonIgnore
//	@OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@OrderBy(value = "sort ASC")
//	private List<ProductImage> images;
//
//	/**
//	 *
//	 * 商品详情图片
//	 *
//	 */
//	@JsonIgnore
//	@OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@OrderBy(value = "sort ASC")
//	private List<ProductMedia> medias;


	/**
	 *
	 * 产品所在地址
	 *
	 */
//	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
//	@JoinColumn(name = "addressId")
//	private ProductAddress address;
	private Long addressId;

	/**
	 *
	 * 销售地址
	 *
	 */
//	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
//	@JoinColumn(name = "salesAddressId")
//	private ProductSalesAddress salesAddress;
	private Long salesAddressId;


	/**
	 *
	 * 位置经纬度
	 *
	 */
	@Embedded
	private Location location;


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
	 * 商品类型
	 *
	 */
	private int type;


	/**
	 *
	 * 二维码地址
	 *
	 */
	private String qrCode;

	/**
	 *
	 * 商品H5地址
	 *
	 */
	private String url;

	/**
	 *
	 * 标题拼音
	 *
	 */
	private String letter;

	/**
	 *
	 * 商品条码
	 * 
	 */
	private String barcode;

	/**
	 * 
	 * 商品图片
	 * 
	 */
	@Column(nullable = false)
	private String image;

	/**
	 *
	 * 主图视频
	 *
	 */
	private String video;

	/**
	 *
	 * 商品标题
	 * 
	 */
	@Column(nullable = false)
	private String title;

	/**
	 * 商品卖点-副标题
	 * 
	 */
	private String subtitle;

	/**
	 *
	 * 商品描述
	 *
	 */
	@Column(length = 1000)
	private String description;

	/**
	 *
	 * 商品详情H5
	 *
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String details;

	/**
	 *
	 * 商品标签，逗号隔开
	 *
	 */
	private String marks;


	/**
	 *
	 * 分销系统中使用
	 * 佣金类型 0固定/1按比例
	 *
	 */
	private Integer commissionType;

	/**
	 *
	 * 分销系统中使用
	 * 佣金 按比例(0~1)
	 *
	 */
	private Double commissionRate;

	/**
	 *
	 * 分销系统中使用
	 * 佣金 固定金额
	 *
	 */
	private Double commissionAmount;


	/**
	 *
	 * 商品价格
	 *
	 */
	private double price;

	/**
	 *
	 * 商品最高价格
	 *
	 */
	private double maxPrice;


	/**
	 *
	 * 单价（房屋销售里的每平方米单价）
	 *
	 */
	private Double unitPrice;

	/**
	 *
	 * 市场价
	 *
	 */
	private Double marketPrice;

	/**
	 *
	 * 成本价
	 *
	 */
	private Double costPrice;

	/**
	 *
	 * 商品库存
	 *
	 */
	private long stock;


	/**
	 *
	 * 告警库存
	 *
	 */
	private Long alarmStock;

	/**
	 *
	 * 净重 KG
	 *
	 */
	private Double weight;

	/**
	 *
	 * 毛重 KG
	 *
	 */
	private Double roughWeight;

	/**
	 *
	 * 体积 M3
	 *
	 */
	private Double volume;

	/**
	 *
	 * 支持商品订制
	 *
	 */
	private Boolean custom;


	/**
	 *
	 * 上市时间
	 *
	 */
	private Date marketTime;

	/**
	 *
	 * 交付时间
	 * 预售商品交付时间
	 *
	 */
	private Date deliveryTime;


	/**
	 *
	 * 状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核失败)
	 *
	 */
	private int status;

	/**
	 *
	 * 状态备注
	 *
	 */
	private String statusRemark;


	/**
	 *
	 * 商品是否上架
	 * 未上架商品不会在前端展示
	 * 已经上架商品会在前端展示
	 *
	 *
	 */
	private boolean onSell;

	/**
	 *
	 * 销售状态（0待售/1在售/2售罄）
	 *
	 */
	private int salesStatus;

	/**
	 *
	 * 销售量
	 *
	 */
	private int sales;


	/**
	 * 
	 * 创建时间
	 * 
	 */
	private Date createTime;

	/**
	 * 更新时间
	 * 
	 */
	private Date updateTime;

	/**
	 *
	 * 是否启用仓库库存
	 *
	 */
	private boolean useStoreStock;




	@Override
	public Item toItem() {
		return new Item(id,"Product",title);
	}



}
