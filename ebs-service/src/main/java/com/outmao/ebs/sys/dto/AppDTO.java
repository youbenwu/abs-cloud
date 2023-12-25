package com.outmao.ebs.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class AppDTO
{


    private Long id;

    /**
     * 唯一编码 迁眼用户端小程序--qy-wechat
     */
    @ApiModelProperty(name = "code", value = "唯一编码 迁眼用户端小程序--qy-wechat")
    private String code;

    /**
     * 0--开发 1--测试 2--提审 3--上线
     */
    @ApiModelProperty(name = "status", value = "0--开发 1--测试 2--提审 3--上线")
    private int status;

    /**
     * 应用名称
     */
    private String name;




}
