package com.outmao.ebs.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 身份证实名认证资料
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_IdCard")
public class IdCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    //用户
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	//认证状态
	private int status;
	//状态备注
	private String statusRemark;
	//姓名
	private String name;
	//身份证号码
	private String idCardNo;
	//正面/反面
	private String images;
	//时间
	private Date createTime;
	//时间
	private Date updateTime;

}
