package com.outmao.ebs.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertPvLogDTO {

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "advertType", value = "广告类型 0--平台广告 1--企业广告 2--个人广告")
    private Integer advertType;

    @ApiModelProperty(name = "amount", value = "用来计算广告收益")
    private double amount;

    @ApiModelProperty(name = "userId", value = "点击用户")
    private Long userId;

    @ApiModelProperty(name = "spaceId", value = "点击场所ID,传酒店ID")
    private Long spaceId;

    @ApiModelProperty(name = "type", value = "类型 0--爆光 1--点击广告链接 2--点击广告视频 3--扫广告二维码")
    private int type;

    private int incomeType;


}
