package com.outmao.ebs.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "m_UserMessage", uniqueConstraints = { @UniqueConstraint(columnNames = { "toId", "messageId" }) })
public class UserMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "fromId")
	private User from;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "toId")
	private User to;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "messageId")
	private Message message;
	//手机号或邮箱
	private String target;
	private String type;
	//类型 0--站内信 1--电子邮件 2--短信 3--推送 4--小程序消息
	private int sendType;
	private int status;
	private Date createTime;
    private Date updateTime;

}
