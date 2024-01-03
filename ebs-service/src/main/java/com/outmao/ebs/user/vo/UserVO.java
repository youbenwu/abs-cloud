package com.outmao.ebs.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "UserVO", description = "用户基本信息")
@JsonInclude(value = Include.NON_NULL)
@Data
public class UserVO extends SimpleUserVO{

	@ApiModelProperty(name = "id", value = "用户ID")
	private Long    id;

	@ApiModelProperty(name = "walletId", value = "钱包ID")
	private Long    walletId;

	@ApiModelProperty(name = "subjectId", value = "朋友圈主题ID")
	private Long subjectId;

	@ApiModelProperty(name = "username", value = "帐号")
	private String  username;

	@ApiModelProperty(name = "nickname", value = "用户昵称")
	private String  nickname;

	@ApiModelProperty(name = "avatar", value = "用户头像")
	private String  avatar;

	@ApiModelProperty(name = "areaCode", value = "所在地区(城市)号")
	private String  areaCode;

	@ApiModelProperty(name = "area", value = "所在地区(城市)名称")
	private String  area;

	@ApiModelProperty(name = "qrCode", value = "二维码地址")
	private String  qrCode;

	@ApiModelProperty(name = "url", value = "用户信息地址")
	private String  url;

	@ApiModelProperty(name = "verified", value = "是否实名认证")
	private Boolean verified;

	@ApiModelProperty(name = "entVerified", value = "是否企业认证")
	private Boolean entVerified;

	@ApiModelProperty(name = "credits", value = "用户成长值")
	private Long    credits;

	@ApiModelProperty(name = "vip", value = "用户VIP等级")
	private Integer vip;

	@ApiModelProperty(name = "level", value = "用户等级")
	private Integer level;

	@ApiModelProperty(name = "registerTime", value = "用户注册时间")
	private Date    registerTime;

	@ApiModelProperty(name = "loginTime", value = "用户登录时间")
	private Date    loginTime;

	@ApiModelProperty(name = "status", value = "帐号状态0--正常1--禁用")
	private Integer status;

	@ApiModelProperty(name = "type", value = "帐号类型")
	private Integer type;

	@ApiModelProperty(name = "imei", value = "imei")
	private String  imei;

	@ApiModelProperty(name = "online", value = "是否在线")
	private Boolean online;



}
