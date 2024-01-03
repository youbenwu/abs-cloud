package com.outmao.ebs.mall.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.bbs.common.data.BindingSubjectId;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Shop")
public class Shop implements Serializable , BindingSubjectId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 店铺ID
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(updatable = false,nullable = false)
	private Long orgId;

	/**
	 *
	 * 店铺所属商家
	 *
	 */
	@Column(updatable = false,nullable = false)
	private Long merchantId;

	/**
	 *
	 * 店铺所属用户
	 *
	 */
	@Column(updatable = false,nullable = false)
	private Long userId;

	/**
	 *
	 * 主题ID
	 *
	 */
	private Long subjectId;


	/**
	 *
	 * 店铺状态
	 *
	 */
	private int status= Status.NotAudit.getStatus();

	/**
	 *
	 * 店铺备注
	 *
	 */
	private String statusRemark= Status.NotAudit.getStatusRemark();

	/**
	 *
	 * 是否启用仓库库存
	 *
	 */
	private boolean useStoreStock;

	/**
	 *
	 * 店铺标题
	 * 
	 */
	@Column(unique = true)
	private String title;

	/**
	 *
	 * 店铺副标题
	 *
	 */
	private String subtitle;

	/**
	 *
	 * 店铺简介
	 *
	 */
	@Lob
	private String intro;

	/**
	 *
	 * 店铺图标
	 * 
	 */
	private String logo;
	
	/**
	 *
	 * 店铺封面
	 * 
	 */
	private String image;

	/**
	 *
	 * 店铺H5地址
	 *
	 */
	private String url;

	/**
	 *
	 * 店铺二维码地址，扫码访问店铺首页
	 *
	 */
	private String qrCode;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;


	@Override
	public Item toItem() {
		return new Item(id,"Shop",title);
	}



}
