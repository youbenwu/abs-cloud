package com.outmao.ebs.message.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息模板
 */
@Data
public class MessageTemplateVO implements Serializable {


	private Long id;


	private Long typeId;

	/**
	 *
	 * 发送类型
	 *
	 */
	private int sendType;

	/**
	 *
	 * 模板名称
	 *
	 */
	private String name;

	/**
	 *
	 * 外部系统模板ID
	 * 如：短信模板ID/小程序消息模板ID
	 *
	 */
	private String templateId;

	/**
	 *
	 * 消息标题
	 *
	 */
	private String title;

	/**
	 *
	 * 消息内容
	 *
	 */
	private String content;

	/**
	 *
	 * 消息地址
	 *
	 */
	private String url;

	@ApiModelProperty(name = "action", value = "按钮名称")
	private String action;

	private Date createTime;
	private Date updateTime;


}
