package com.outmao.ebs.message.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.message.entity.MessageTemplateTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value = "MessageTypeVO", description = "消息类型对象")
public class MessageTypeVO implements Serializable {

    @ApiModelProperty(name = "id", value = "类型ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "0--正常 1--禁用")
    private int status;

    @ApiModelProperty(name = "status", value = "模板标签")
    private List<MessageTemplateTagVO> tags;

    @ApiModelProperty(name = "status", value = "类型名称")
    private String name;

    @ApiModelProperty(name = "title", value = "类型标题")
    private String title;

    @ApiModelProperty(name = "description", value = "类型描述")
    private String description;

    @ApiModelProperty(name = "msg", value = "是否发送站内信")
    private boolean msg;

    @ApiModelProperty(name = "email", value = "是否发送邮件")
    private boolean email;

    @ApiModelProperty(name = "sms", value = "是否发送短信")
    private boolean sms;

    @ApiModelProperty(name = "push", value = "是否推送消息")
    private boolean push;

    @ApiModelProperty(name = "mp", value = "是否微信消息")
    private boolean mp;

    private Long msgTemplateId;
    @ApiModelProperty(name = "msgTemplate", value = "站内信模板")
    private MessageTemplateVO msgTemplate;

    private Long emailTemplateId;
    @ApiModelProperty(name = "emailTemplate", value = "邮件模板")
    private MessageTemplateVO emailTemplate;

    private Long smsTemplateId;
    @ApiModelProperty(name = "smsTemplate", value = "短信模板")
    private MessageTemplateVO smsTemplate;

    private Long pushTemplateId;
    @ApiModelProperty(name = "pushTemplate", value = "推送模板")
    private MessageTemplateVO pushTemplate;

    private Long mpTemplateId;
    @ApiModelProperty(name = "mpTemplate", value = "微信消息模板")
    private MessageTemplateVO mpTemplate;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


}
