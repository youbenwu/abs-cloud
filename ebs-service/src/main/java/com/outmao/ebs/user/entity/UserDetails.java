package com.outmao.ebs.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户资料
 */
@Entity
@Table(name = "ebs_UserDetails")
@Data
public class UserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 自动ID
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long  id;

	/**
	 *
	 * 用户
	 *
	 */
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 认证的企业ID
	 */
	private Long enterpriseId;

	/**
	 * 认证的企业名称
	 */
	private String enterpriseName;

	/**
	 * 用户绑定手机号
	 */
	private String phone;

	/**
	 *
	 * 住址
	 *
	 */
	private String address;

	/**
	 * 用户邮箱
	 */
	private String email;

	/**
	 * 微信号
	 */
	private String weChat;

	/**
	 * QQ号
	 */
	private String qq;


	/**
	 * 0未知1男2女
	 */
	private int sex;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 故乡 格式 广东-广州
	 */
	private String hometown;

	/**
	 * 职业
	 */
	private String career;

	/**
	 * 学校
	 */
	private String school;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 职位
	 */
	private String job;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 个人爱好
	 */
	private String hobby;


	/**
	 * 更新时间
	 */
	private Date updateTime;


}
