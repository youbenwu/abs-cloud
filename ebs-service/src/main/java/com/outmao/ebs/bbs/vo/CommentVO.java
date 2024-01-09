package com.outmao.ebs.bbs.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.entity.CommentStats;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "CommentVO", description = "评论信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class CommentVO implements SimpleUserSetter {

	@ApiModelProperty(name = "id", value = "评论ID")
	private Long id;

	@ApiModelProperty(name = "userId", value = "用户ID")
	private Long userId;

	@ApiModelProperty(name = "user", value = "用户信息")
	private SimpleUserVO user;

	@ApiModelProperty(name = "postId", value = "帖子ID")
	private Long postId;

	@ApiModelProperty(name = "toId", value = "被回复评论ID")
	private Long toId;

	@ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除")
	private int status;

	@ApiModelProperty(name = "images", value = "评论图片，逗号隔开")
	private String images;

	@ApiModelProperty(name = "movie", value = "评论视频")
	private String video;

	@ApiModelProperty(name = "content", value = "评论内容")
	private String content;

	@ApiModelProperty(name = "createTime", value = "评论时间")
	private Date createTime;

	@ApiModelProperty(name = "updateTime", value = "更新时间")
	private Date updateTime;

	@ApiModelProperty(name = "stats", value = "统计数据")
	private CommentStats stats;

	@ApiModelProperty(name = "like", value = "是否点赞")
	private Boolean like;

	@ApiModelProperty(name = "dislike", value = "是否反对")
	private Boolean dislike;


}
