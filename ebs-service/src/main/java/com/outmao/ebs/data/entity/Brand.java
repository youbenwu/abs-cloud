package com.outmao.ebs.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author deel
 *
 *         品牌
 *
 */
@Data
@Entity
@Table(name = "ebs_Brand")
public class Brand implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/**
	 *
	 * 状态
	 *
	 */
	private int status;

	/**
	 *
	 * 状态
	 *
	 */
	private String statusRemark;

	private int                   type;// 类型 0: Inland在中国申请或注册； 1: Foreign在国外申请或注册
	@Column(unique = true)
	private String                name;// 品牌名
	private String                nameCn;// 品牌名-中文名
	private String                nameEn;// 品牌名-英文名
	private String                image;// 品牌图片
	private String                birthplace;// 品牌发源地/国家名称
	private String                owner;// 品牌所有人
	@Column(unique = true)
	private String                trademark;// 商标注册号/申请号
	private String                license;// 商标注册证/受理通知书正面和背面,逗号隔开
	private String                entryBill;// 报关单
	private String                packaging;// 商品外包装图片,逗号隔开
	private Date                  createTime;
	private Date                  updateTime;


}
