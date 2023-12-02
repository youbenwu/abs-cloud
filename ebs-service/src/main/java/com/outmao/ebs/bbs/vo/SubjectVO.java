package com.outmao.ebs.bbs.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.entity.SubjectStats;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "SubjectVO", description = "主题信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SubjectVO implements SimpleUserSetter {


	/**
	 * 自动编号
	 */
	@ApiModelProperty(name = "id", value = "ID")
	private Long id;

	@ApiModelProperty(name = "userId", value = "用户ID")
	private Long userId;

	@ApiModelProperty(name = "user", value = "用户")
	private SimpleUserVO user;

	/**
	 * 可以绑定业务对像
	 */
	@ApiModelProperty(name = "item", value = "绑定业务对像")
	private BindingItem item;

	@ApiModelProperty(name = "type", value = "0--论坛 1--朋友圈 2--商品评价")
	private int type;

	/**
	 * 标题
	 */
	@ApiModelProperty(name = "title", value = "标题")
	private String title;

	/**
	 * 内容
	 */
	@ApiModelProperty(name = "content", value = "内容")
	private String content;

	/**
	 *
	 * 主题图片
	 *
	 */
	@ApiModelProperty(name = "image", value = "主题图片")
	private String image;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(name = "updateTime", value = "更新时间")
	private Date updateTime;

	/**
	 * 数据统计
	 */
	@ApiModelProperty(name = "stats", value = "数据统计")
	private SubjectStats stats;

	/**
	 * 是否关注
	 */
	@ApiModelProperty(name = "fav", value = "是否关注")
	private Boolean fav;

	/**
	 * 是否点赞
	 */
	@ApiModelProperty(name = "like", value = "是否点赞")
	private Boolean like;

	/**
	 * 是否反对
	 */
	@ApiModelProperty(name = "dislike", value = "是否反对")
	private Boolean dislike;


}
