package com.outmao.ebs.qrCode.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ebs_QrCode")
public class QrCode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//二维码ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long   id;

	//状态 0--未激活；1--已激活
	private int status;

	//状态 0--未激活；1--已激活
	private String statusRemark;
	
	//二维码图片地址
	private String path;

	//二维码的值
	@Column(unique = true)
	private String code;

	//目标URL
	private String url;

	private String business;

	//激活时间
	private Date   activateTime;
	
	//生成时间
	private Date   createTime;

	//更新时间
	private Date   updateTime;


}
