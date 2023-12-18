package com.outmao.ebs.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "m_Message")
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//组织内部消息
	private Long orgId;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "fromId")
	private User from;
	//接收用户ID列表JSON
	private String tos;
	private int status;
	private String statusRemark;
	private String type;
	//类型 0--站内信 1--电子邮件 2--短信 3--推送 4--小程序消息
	private int sendType;
	//短信模板ID/小程序消息模板ID
	private String templateId;
	@Embedded
	private BindingItem item;
	private String image;
	private String title;
	@Lob
	private String content;
	private String url;
	/**
	 *
	 * 搜索关键字
	 *
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String keyword;
	private Date createTime;
	private Date updateTime;


}
