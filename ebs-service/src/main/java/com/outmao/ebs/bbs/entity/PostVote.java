package com.outmao.ebs.bbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "bbs_PostVote", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "userId", "postId" }) })
public class PostVote implements Serializable{
	
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
	@JoinColumn(name = "postId")
	private Post post;

	/**
	 * 类型 0点赞1反对
	 */
	private int vote;

	/**
	 * 时间
	 */
	private Date createTime;


	

}
