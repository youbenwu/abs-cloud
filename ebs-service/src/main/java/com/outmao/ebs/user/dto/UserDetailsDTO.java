package com.outmao.ebs.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "UserDetailsDTO", description = "修改用户资料参数")
@Data
public class UserDetailsDTO {

    @ApiModelProperty(name = "id", value = "用户ID")
	private Long    id;
    @ApiModelProperty(name = "avatar", value = "用户头像")
	private String  avatar;
    @ApiModelProperty(name = "nickname", value = "用户昵称")
	private String  nickname;
	@ApiModelProperty(name = "weChat", value = "微信号")
	private String  wechat;
	@ApiModelProperty(name = "qq", value = "QQ号")
	private String  qq;
    @ApiModelProperty(name = "phone", value = "手机号")
	private String  phone;
    @ApiModelProperty(name = "email", value = "电子邮箱")
	private String  email;
    @ApiModelProperty(name = "realName", value = "姓名")
	private String  realName;
    @ApiModelProperty(name = "sex", value = "姓别0--未知 1--男 2--女")
	private Integer sex;
	@ApiModelProperty(name = "area", value = "所在地区")
	private String  area;
    @ApiModelProperty(name = "birthday", value = "生日")
	private Date    birthday;
    @ApiModelProperty(name = "hometown", value = "出生地")
	private String  hometown;
    @ApiModelProperty(name = "address", value = "住址")
	private String  address;
	@ApiModelProperty(name = "career", value = "职业")
	private String  career;
    @ApiModelProperty(name = "school", value = "学校")
	private String  school;
    @ApiModelProperty(name = "company", value = "公司")
	private String  company;
    @ApiModelProperty(name = "job", value = "职位")
	private String  job;
	/**
	 * 年龄
	 */
	@ApiModelProperty(name = "age", value = "年龄")
	private Integer age;

	/**
	 * 个人爱好
	 */
	@ApiModelProperty(name = "hobby", value = "个人爱好")
	private String hobby;

}
