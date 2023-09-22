package com.outmao.ebs.bbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.data.entity.Category;
import com.outmao.ebs.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 主题
 *
 */

@Data
@Entity
@Table(name = "bbs_Subject", uniqueConstraints = { @UniqueConstraint(columnNames = { "item_id", "item_type" }) })
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自动编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long  id;

	/**
	 * 发表用户
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Category category;

	/**
	 * 发表用户
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/**
	 * 统计数据
	 */
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JoinColumn(name = "statsId")
	private SubjectStats stats;

	/**
	 * 可以绑定业务对像
	 */
	@Embedded
	private BindingItem item;



	/**
	 *
	 * 标题
	 *
	 */
	@Column(nullable = false)
	private String title;

	/**
	 *
	 * 详情
	 *
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String content;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;


}
