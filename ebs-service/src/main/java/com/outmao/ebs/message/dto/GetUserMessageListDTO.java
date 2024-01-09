package com.outmao.ebs.message.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetUserMessageListDTO {

    private Long userId;

    private List<String> types;

    @ApiModelProperty(name = "status", value = "0未读1已读")
    private Integer status;

}
