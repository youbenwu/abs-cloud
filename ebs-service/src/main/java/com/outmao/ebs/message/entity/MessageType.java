package com.outmao.ebs.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 * 消息类型
 *
 *
 */
@Data
@Entity
@Table(name = "m_MessageType")
public class MessageType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int status;
	@JsonIgnore
	@OneToMany(mappedBy = "type", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<MessageTemplateTag> tags;
	@Column(unique=true)
	private String name;
	private String title;
	private String description;
	private boolean msg;
	private boolean email;
	private boolean sms;
	private boolean push;
	private boolean mp;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "msgTemplateId")
	private MessageTemplate msgTemplate;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "emailTemplateId")
	private MessageTemplate emailTemplate;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,  fetch = FetchType.LAZY)
	@JoinColumn(name = "smsTemplateId")
	private MessageTemplate smsTemplate;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,  fetch = FetchType.LAZY)
	@JoinColumn(name = "pushTemplateId")
	private MessageTemplate pushTemplate;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,  fetch = FetchType.LAZY)
	@JoinColumn(name = "mpTemplateId")
	private MessageTemplate mpTemplate;
	private Date createTime;
	private Date updateTime;

}
