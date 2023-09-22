package com.outmao.ebs.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SimpleUserVO", description = "用户简单信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SimpleUserVO{

    @ApiModelProperty(name = "id", value = "用户ID")
    private Long   id;

    @ApiModelProperty(name = "area", value = "所在地区")
    private String area;

    @ApiModelProperty(name = "username", value = "帐号")
    private String username;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "avatar", value = "用户头像")
    private String avatar;


}
