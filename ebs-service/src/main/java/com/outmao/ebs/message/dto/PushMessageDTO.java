package com.outmao.ebs.message.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(value = Include.NON_NULL)
@ApiModel(value = "PushMessageDTO", description = "推送消息对象")
public class PushMessageDTO {


	@ApiModelProperty(name = "type", value = "消息类型")
	private String type;

	// 关联的对象
	@ApiModelProperty(name = "item", value = "关联的对象")
	private BindingItem item;

	@ApiModelProperty(name = "url", value = "URL")
	private String url;

	@ApiModelProperty(name = "title", value = "消息标题")
	private String title;

	@ApiModelProperty(name = "content", value = "消息内容")
	private String content;

	@ApiModelProperty(name = "image", value = "消息图片")
	private String image;

	@ApiModelProperty(name = "action", value = "按钮名称")
	private String action;


}
