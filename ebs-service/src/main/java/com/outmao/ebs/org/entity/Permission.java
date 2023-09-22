package com.outmao.ebs.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 定义权限对象 多级树型结构
 *
 */
@Data
@Entity
@Table(name = "ebs_Permission", uniqueConstraints = {@UniqueConstraint(columnNames = { "url","name" })})
public class Permission implements Serializable {

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

	/**
	 *
	 * 上级
	 *
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Permission parent;


	/**
	 *
	 * 多级分类中所处的级别，级别从0开始
	 *
	 */
	private int level=0;

	/**
	 *
	 * 多级分类中是否是叶子节点的标识
	 *
	 */
	private boolean leaf=true;

	/**
	 *
	 * 子权限
	 *
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Permission> children;

	/**
	 * 排序 从0开始
	 */
	private int sort;

	/**
	 * 0--功能 1--菜单 2--页面
	 */
	private int type;

	/**
	 *
	 * 权限资源URL
	 *
	 */
	@Column(nullable = false)
	private String url;

	/**
	 *
	 * 权限名称
	 *
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 权限标题
	 */
	@Column(nullable = false)
	private String title;

	/**
	 * 权限描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;


}
