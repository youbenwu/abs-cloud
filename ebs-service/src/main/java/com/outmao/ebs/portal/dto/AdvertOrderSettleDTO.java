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

    @ApiModelProperty(name = "contentType", value = "0--图文 1--图文视频 2--图文视频链接 3--图文视频二维码 4--图文视频链接二维码 5--图文链接 6--图文二维码")
    private int contentType;

    @ApiModelProperty(name = "startTime", value = "广告展示开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "广告展示结束时间")
    private Date endTime;

    @ApiModelProperty(name = "quantity", value = "PV数量")
    private int quantity;


}
