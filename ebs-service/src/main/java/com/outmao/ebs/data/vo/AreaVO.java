package com.outmao.ebs.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



@JsonInclude(value = Include.NON_NULL)
@Data
public class AreaVO implements Serializable {

	private Long id;


	private Long parentId;


	private List<AreaVO> children;

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
