package com.outmao.ebs.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 角色
 */
@Data
@Entity
@Table(name = "ebs_Role", uniqueConstraints = {@UniqueConstraint(columnNames = { "orgId", "name" }) })
public class Role implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 *
	 * 所属组织
	 *
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "orgId")
	private Org org;

	/**
	 * 角色权限列表
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<RolePermission> permissions;

	/**
	 * 排序 从0开始
	 */
	private int sort;

	/**
	 * 角色标题
	 */
	@Column(nullable = false,length = 50)
	private String title;

	/**
	 * 角色描述
	 */
	@Column(length = 200)
	private String description;

	/**
	 * 角色值
	 */
	@Column(nullable = false,length = 50)
	private String name;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;


}