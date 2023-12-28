package com.outmao.ebs.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息模板
 */
@Data
@Entity
@Table(name = "m_MessageTemplate")
public class MessageTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	private MessageType type;

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
	@Lob
	private String content;

	/**
	 *
	 * 消息地址
	 *
	 */
	private String url;

	/**
	 *
	 * 按钮名称
	 *
	 */
	private String action;

	private Date createTime;
	private Date updateTime;


}
