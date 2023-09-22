package com.outmao.ebs.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "UserDetailsVO", description = "用户详细资料")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class UserDetailsVO extends UserVO{

	@ApiModelProperty(name = "phone", value = "手机号")
	private String      phone;
	@ApiModelProperty(name = "EMAIL", value = "电子邮箱")
	private String      email;
	@ApiModelProperty(name = "realName", value = "真实姓名")
	private String      realName;
	@ApiModelProperty(name = "sex", value = "姓别0--未知 1--男 2--女")
	private Integer     sex;
	@ApiModelProperty(name = "birthday", value = "生日")
	private Date        birthday;
	@ApiModelProperty(name = "hometown", value = "出生地")
	private String      hometown;
	@ApiModelProperty(name = "address", value = "住址")
	private String      address;
	@ApiModelProperty(name = "weChat", value = "微信号")
	private String      weChat;
	@ApiModelProperty(name = "qq", value = "QQ号")
	private String      qq;
	@ApiModelProperty(name = "career", value = "职业")
	private String      career;
	@ApiModelProperty(name = "school", value = "学校")
	private String      school;
	@ApiModelProperty(name = "company", value = "公司")
	private String      company;
	@ApiModelProperty(name = "job", value = "职位")
	private String      job;
	@ApiModelProperty(name = "updateTime", value = "修改时间")
	private Date        updateTime;



}
