package com.outmao.ebs.org.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限付给角色
 */
@Data
@Entity
@Table(name = "ebs_RoleMenu", uniqueConstraints = {@UniqueConstraint(columnNames = { "roleId", "menuId" }) })
public class RoleMenu implements Serializable {

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

    @Column(updatable = false,nullable = false)
	private Long roleId;

	@Column(updatable = false,nullable = false)
	private Long menuId;

	/**
	 * 创建时间
	 */
	private Date createTime;


}
