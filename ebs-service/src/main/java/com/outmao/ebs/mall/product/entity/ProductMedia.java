package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 商品详情图片
 *
 */
@Data
@Entity
@Table(name = "ebs_ProductMedia")
public class ProductMedia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自动编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	/**
	 * 文件ID
	 */
	private Long mediaId;

	/**
	 * 文件URL
	 */
	@Column(nullable = false)
	private String url;

	/**
	 * 名称
	 */
	private String title;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 0图片，1视频
	 */
	private int type;

	/**
	 * 状态 0显示，1隐藏
	 */
	private int status;

	/**
	 * 排序
	 */
	private int sort;

	/**
	 * 图片创建时间
	 */
	private Date createTime;


}
