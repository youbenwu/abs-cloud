package com.outmao.ebs.message.dto;

import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MessageDTO", description = "发送消息对象")
public class MessageDTO {

	@ApiModelProperty(name="orgId",value="只发送给组织内的成员")
	private Long   orgId;
	@ApiModelProperty(name="fromId",value="发送用户ID")
	private Long   fromId;
	@ApiModelProperty(name="tos",value="接收用户ID列表，为空则发送全部用户")
	private List<Long> tos;
	@ApiModelProperty(name="sendType",value="消息发送类型 0--站内信 1--电子邮件 2--短信 3--推送 4--小程序消息")
	private int    sendType;
	@ApiModelProperty(name="type",value="消息类型，公告传--notice")
	private String type;
	@ApiModelProperty(name="templateId",value="短信模板ID/小程序消息模板ID")
	private String templateId;
	@ApiModelProperty(name="title",value="消息标题")
	private String title;
	@ApiModelProperty(name="content",value="消息内容")
	private String content;
	@ApiModelProperty(name="image",value="消息图片")
	private String image;
	@ApiModelProperty(name="title",value="消息地址")
	private String url;
	@ApiModelProperty(name = "action", value = "按钮名称")
	private String action;
	@ApiModelProperty(name="title",value="消息绑定对象")
	private BindingItem item;


}
