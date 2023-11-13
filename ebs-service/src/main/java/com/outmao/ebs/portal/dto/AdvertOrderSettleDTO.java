package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AdvertOrderSettleDTO {


    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "type", value = "广告类型 1--企业广告 2--个人广告")
    private int type;

    @ApiModelProperty(name = "url", value = "广告跳转地址")
    private String url;

    @ApiModelProperty(name = "video", value = "广告视频地址")
    private String video;

    @ApiModelProperty(name = "qrCode", value = "广告二维码地址")
    private String qrCode;

    @ApiModelProperty(name = "startTime", value = "广告展示开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "广告展示结束时间")
    private Date endTime;

    @ApiModelProperty(name = "quantity", value = "PV数量")
    private int quantity;


}
