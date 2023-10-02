package com.outmao.ebs.org.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private Role role;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "menuId")
	private Menu menu;

	/**
	 * 创建时间
	 */
	private Date createTime;


}
