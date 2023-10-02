package com.outmao.ebs.mall.promotion.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class SetGiftsStatusDTO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 状态 0--正常 1--已下架
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--正常 1--已下架")
    private int status;



}
