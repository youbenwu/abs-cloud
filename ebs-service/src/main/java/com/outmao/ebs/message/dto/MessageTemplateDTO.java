package com.outmao.ebs.message.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MessageTemplateDTO", description = "保存消息模板参数")
public class MessageTemplateDTO {

	@ApiModelProperty(name="id",value="模板ID")
	private Long id;

	@ApiModelProperty(name="typeId",value="消息类型ID")
	private Long typeId;

	@ApiModelProperty(name="sendType",value="消息发送类型 0--站内信 1--电子邮件 2--短信 3--推送")
	private int sendType;

	@ApiModelProperty(name="name",value="模板名称")
	private String name;

	@ApiModelProperty(name="title",value="消息标题模板")
	private String title;

	@ApiModelProperty(name="content",value="消息内容模板")
	private String content;

	@ApiModelProperty(name="url",value="消息详情URL模板")
	private String url;

	@ApiModelProperty(name = "action", value = "按钮名称")
	private String action;

}
