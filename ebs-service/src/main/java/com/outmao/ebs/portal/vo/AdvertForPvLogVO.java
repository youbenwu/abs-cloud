package com.outmao.ebs.portal.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 广告
 *
 */
@Data
public class AdvertForPvLogVO  {

    private Long id;

    @ApiModelProperty(name = "type", value = "广告类型 0--平台广告 1--企业广告 2--个人广告")
    private int type;

    private Double buyPrice;



}
