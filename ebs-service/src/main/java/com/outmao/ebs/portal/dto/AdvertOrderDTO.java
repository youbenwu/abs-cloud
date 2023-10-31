package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class AdvertOrderDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;


    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "type", value = "广告类型 0--系统广告 1--企业广告 2--个人广告")
    private int type;

    @ApiModelProperty(name = "advertType", value = "广告类型 PPV--普通图片视频广告 PPC--带广告链接 PPA--带二维码")
    private String advertType;

    @ApiModelProperty(name = "title", value = "广告名称")
    private String title;

    @ApiModelProperty(name = "subtitle", value = "广告副标题")
    private String subtitle;

    @ApiModelProperty(name = "image", value = "图片地址")
    private String image;

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


    @ApiModelProperty(name = "settleId", value = "结算ID")
    private Long settleId;


}