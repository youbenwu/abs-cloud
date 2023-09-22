package com.outmao.ebs.bbs.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.entity.PostStats;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "PostVO", description = "帖子信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class PostVO implements SimpleUserSetter {

	/**
	 * 自动编号
	 */
	@ApiModelProperty(name = "id", value = "帖子ID")
	private Long id;

	@ApiModelProperty(name = "userId", value = "用户ID")
	private Long userId;

	@ApiModelProperty(name = "user", value = "用户信息")
	private SimpleUserVO user;

	@ApiModelProperty(name = "subjectId", value = "主题ID")
	private Long subjectId;

	/**
	 * 绑定业务对像
	 */
	@ApiModelProperty(name = "item", value = "绑定业务对像")
	private BindingItem item;

	/**
	 * 状态
	 */
	@ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除")
	private int status;

	/**
	 *
	 * 是否置顶
	 *
	 */
	@ApiModelProperty(name = "top", value = "是否置顶")
	private boolean top;

	/**
	 *
	 * 标签
	 *
	 */
	@ApiModelProperty(name = "mark", value = "标签")
	private String mark;

	/**
	 * 帖子标题
	 */
	@ApiModelProperty(name = "title", value = "帖子标题")
	private String title;

	/**
	 * 帖子内容
	 */
	@ApiModelProperty(name = "content", value = "帖子内容")
	private String content;

	/**
	 * 帖子图片
	 */
	@ApiModelProperty(name = "images", value = "帖子图片")
	private String images;

	@ApiModelProperty(name = "video", value = "帖子视频")
	private String video;

	/**
	 * 发帖时间
	 */
	@ApiModelProperty(name = "createTime", value = "发帖时间")
	private Date createTime;

	/**
	 * 最后回复时间
	 */
	@ApiModelProperty(name = "updateTime", value = "最后回复时间")
	private Date updateTime;

	/**
	 * 统计数据
	 */
	@ApiModelProperty(name = "stats", value = "统计数据")
    private PostStats stats;
	
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
