package com.outmao.ebs.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MessageTypeDTO", description = "保存消息类别参数")
public class MessageTypeDTO {

	@ApiModelProperty(name="id",value="类型ID")
	private Long id;

	@ApiModelProperty(name="status",value="状态 0--正常 1--禁用")
	private int status;

	@ApiModelProperty(name="name",value="类型名称")
	private String name;

	@ApiModelProperty(name="title",value="类型标题")
	private String title;

	@ApiModelProperty(name="description",value="类型描述")
	private String description;

	@ApiModelProperty(name="tags",value="模板标签")
	private List<MessageTemplateTagDTO> tags;

	@ApiModelProperty(name="msg",value="是否发送站内信")
	private boolean msg;

	@ApiModelProperty(name="email",value="是否发送邮件")
	private boolean email;

	@ApiModelProperty(name="sms",value="是否发送短信")
	private boolean sms;

	@ApiModelProperty(name="push",value="是否推送")
	private boolean push;

	@ApiModelProperty(name="mp",value="是否微信消息")
	private boolean mp;

	@ApiModelProperty(name="msgTemplateId",value="站内信模板ID")
	private Long msgTemplateId;

	@ApiModelProperty(name="emailTemplateId",value="邮件模板ID")
	private Long emailTemplateId;

	@ApiModelProperty(name="smsTemplateId",value="短信模板ID")
	private Long smsTemplateId;

	@ApiModelProperty(name="pushTemplateId",value="推送模板ID")
	private Long pushTemplateId;

	@ApiModelProperty(name="mpTemplateId",value="微信消息模板ID")
	private Long mpTemplateId;


}
