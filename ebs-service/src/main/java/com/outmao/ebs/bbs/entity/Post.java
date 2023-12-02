package com.outmao.ebs.bbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * 帖子
 * */
@Data
@Entity
@Table(name = "bbs_Post")
public class Post implements Serializable {

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
	@JoinColumn(name = "userId")
	private User user;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "subjectId")
	private Subject subject;

	/**
	 * 统计数据
	 */
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JoinColumn(name = "statsId")
	private PostStats stats;

	/**
	 * 可以绑定业务对像
	 */
	@Embedded
	private BindingItem item;

	/**
	 *
	 * 0--论坛 1--朋友圈 2--商品评价
	 *
	 */
	private int type;

	/**
	 *
	 * 状态
	 *
	 */
	private int status;
	
	/**
	 * 
	 * 是否置顶
	 * 
	 */
	private boolean top;

	/**
	 *
	 * 标签
	 *
	 */
	private String mark;

	/**
	 * 帖子标题
	 */
	private String title;

	/**
	 * 帖子内容
	 */
	@Column(length = 1000)
	private String content;

	/**
	 * 帖子图片
	 */
	@Column(length = 500)
	private String images;

	/**
	 * 视频地址
	 */
	private String video;

	/**
	 * 发帖时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;


}
