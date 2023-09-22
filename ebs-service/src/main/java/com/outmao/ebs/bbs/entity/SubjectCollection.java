package com.outmao.ebs.bbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "bbs_SubjectCollection", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "userId", "subjectId" }) })
public class SubjectCollection implements Serializable {

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

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "subjectId")
	private Subject subject;

	/**
	 *
	 * 状态 0--已收藏 1--未收藏
	 *
	 */
	private int status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * subject.user.id
	 */
	private Long subjectUserId;

	/**
	 * subject.item.id
	 */
	private Long itemId;

	/**
	 * subject.item.type
	 */
	private String itemType;

	/**
	 *
	 * 给收藏加标签
	 *
	 */
	private String mark;

	/**
	 *
	 * 用户备注
	 *
	 */
	private String remark;




}
