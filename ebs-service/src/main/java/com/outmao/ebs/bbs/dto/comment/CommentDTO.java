package com.outmao.ebs.bbs.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "CommentDTO", description = "评论参数")
@Data
public class CommentDTO {

	@ApiModelProperty(name = "id", value = "评论ID")
	private Long id;

	@ApiModelProperty(name = "userId", value = "用户的ID")
	private Long userId;

	@ApiModelProperty(name = "postId", value = "贴子ID")
	private Long postId;

	@ApiModelProperty(name = "toId", value = "被回复评论ID")
	private Long toId;

	@ApiModelProperty(name = "content", value = "评论内容")
	private String content;

	@ApiModelProperty(name = "images", value = "评论图片地址，逗号隔开")
	private String images;

	@ApiModelProperty(name = "movie", value = "评论视频地址")
	private String video;

}
