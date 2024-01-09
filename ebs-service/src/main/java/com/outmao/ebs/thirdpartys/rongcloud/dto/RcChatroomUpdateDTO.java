package com.outmao.ebs.thirdpartys.rongcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class RcChatroomUpdateDTO {

    private Long id;

    @ApiModelProperty(name = "name", value = "聊天室名称")
    private String name;



}
