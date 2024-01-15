package com.outmao.ebs.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "QyUserVO", description = "迁眼平板用户信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class QyUserVO {

    @ApiModelProperty(name = "id", value = "用户ID")
    private Long   id;

    @ApiModelProperty(name = "sex", value = "姓别0--未知 1--男 2--女")
    private Integer sex;

    @ApiModelProperty(name = "username", value = "帐号")
    private String username;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "avatar", value = "用户头像")
    private String avatar;



}
