package com.outmao.ebs.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 保存用户私有数据
 *
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_UserData", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "userId", "name" }) })
public class UserData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 自动ID
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 *
	 * 用户
	 *
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/**
	 *
	 * 类型
	 *
	 */
	private String type;

	/**
	 *
	 * 数据项名称
	 *
	 */
	private String name;

	/**
	 *
	 * 数据内容
	 *
	 */
	private String value;

	/**
	 *
	 * 是否选中
	 *
	 */
	private Boolean selected;

	/**
	 *
	 * 备注
	 *
	 */
	private String remark;

	/**
	 *
	 * 时间
	 *
	 */
	private Date createTime;

	/**
	 *
	 * 时间
	 *
	 */
	private Date updateTime;


}
