package com.outmao.ebs.bbs.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.SortEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * 板块
 * */
@Data
@Entity
@Table(name = "bbs_Board")
public class Board extends SortEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 *
	 * 板块类型
	 *
	 * */
	@Column(unique = true)
	private String type;

	/**
	 *
	 * 板块标题
	 *
	 * */
	private String title;

	/**
	 *
	 * 板块图片
	 *
	 * */
	private String image;

	/**
	 *
	 * 板块描述
	 *
	 * */
	private String description;

	/**
	 * 统计数据
	 */
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JoinColumn(name = "statsId")
	private BoardStats stats;


}
