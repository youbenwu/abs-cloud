package com.outmao.ebs.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ebs_Area", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "parentId", "name" }) })
public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//public static final int TYPE_

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Area parent;

	@JsonIgnore
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@OrderBy(value = "id ASC")
	private List<Area> children;

	/**
	 * 多级分类中所处的级别，级别从0开始
	 *
	 */
	private int level;

	/**
	 *
	 * 多级分类中是否是叶子节点的标识
	 *
	 */
	private boolean leaf;

	/**
	 *
	 * 0--国家 1--省级 2--市级 3--区级 4--街道 5--社区
	 *
	 */
	private int type;

	/**
	 *
	 * 是否国外
	 *
	 */
	@Column(name = "_foreign")
	private boolean foreign;

	/**
	 *
	 * 地区名称
	 *
	 */
	private String name;

	/**
	 *
	 * 拼音字母
	 *
	 */
	private String letter;

	/**
	 *
	 * 地区编号
	 *
	 */
	private String areaCode;

	/**
	 *
	 * 国家统计局里的编号
	 *
	 */
	@Column(unique = true)
	private String code;

	/**
	 *
	 * 邮编
	 *
	 */
	private String zipCode;

	/**
	 *
	 * 创建时间
	 *
	 */
	private Date createTime;


}
