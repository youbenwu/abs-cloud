package com.outmao.ebs.mall.manufacturer.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CounselorDTO {


    @ApiModelProperty(name = "id", value = "id")
    private Long id;


    /**
     * 用户
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    /**
     * 所属开发商
     */
    @ApiModelProperty(name = "manufacturerId", value = "所属开发商ID")
    private Long manufacturerId;


    /**
     *
     * 名称
     *
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;


}
