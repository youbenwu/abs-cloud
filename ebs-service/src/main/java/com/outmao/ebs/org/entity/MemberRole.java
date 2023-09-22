package com.outmao.ebs.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 成员角色
 */
@Data
@Entity
@Table(name = "ebs_MemberRole", uniqueConstraints = { @UniqueConstraint(columnNames = { "memberId", "roleId" }) })
public class MemberRole implements Serializable {

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
	@JoinColumn(name = "memberId",updatable = false)
	private Member member;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId",updatable = false)
	private Role role;

	/**
	 * 创建时间
	 */
	private Date createTime;

}
