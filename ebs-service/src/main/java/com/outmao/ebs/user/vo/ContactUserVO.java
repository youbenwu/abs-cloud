package com.outmao.ebs.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "ContactUserVO", description = "用户简单信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ContactUserVO {

    @ApiModelProperty(name = "id", value = "用户ID")
    private Long   id;

    @ApiModelProperty(name = "username", value = "帐号")
    private String username;

    @ApiModelProperty(name = "avatar", value = "用户头像")
    private String avatar;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "phone", value = "用户手机")
    private String phone;

    @ApiModelProperty(name = "realName", value = "实名")
    private String realName;


}
