package com.outmao.ebs.bbs.dto.post;
import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "PostDTO", description = "贴子参数")
@Data
public class PostDTO  {

	@ApiModelProperty(name = "id", value = "贴子ID")
	private Long id;

	@ApiModelProperty(name = "userId", value = "用户的ID")
	private Long userId;

	@ApiModelProperty(name = "subjectId", value = "主题ID")
	private Long subjectId;

	@ApiModelProperty(name = "type", value = "0--论坛 1--朋友圈 2--商品评价")
	private int type;

	@ApiModelProperty(name = "title", value = "标题")
	private String title;

	@ApiModelProperty(name = "content", value = "内容")
	private String content;

	@ApiModelProperty(name = "images", value = "图片，逗号隔开")
	private String images;

	@ApiModelProperty(name = "movie", value = "视频")
	private String video;

	@ApiModelProperty(name = "mark", value = "标签")
	private String mark;

	@ApiModelProperty(name = "item", value = "绑定对象")
	private BindingItem item;
	

}
