package com.outmao.ebs.portal.dto;


import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.Date;

@ApiModel(value = "AdvertDTO", description = "保存广告参数")
@Data
public class AdvertDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;


    /**
     *
     * 排序
     *
     * */
    @ApiModelProperty(name = "sort", value = "排序")
    private int sort;

    /**
     *
     * 0--未上架 1--已上架
     *
     * */
    @ApiModelProperty(name = "status", value = "0--未上架 1--已上架")
    private int status;

    /**
     *
     * 0--无跳转 1--跳转商品
     *
     * */
    @ApiModelProperty(name = "type", value = "0--无跳转 1--跳转商品 2--跳转地址")
    private int type;

    /**
     *
     * 绑定商品 item.id--商品ID item.type--Product
     *
     * */
    @ApiModelProperty(name = "item", value = "绑定商品 item.id--商品ID item.type--Product")
    @Embedded
    private BindingItem item;

    @ApiModelProperty(name = "advertType", value = "广告类型 PPV--普通图片视频广告 PPC--带广告链接 PPA--带二维码")
    private String advertType;

    @ApiModelProperty(name = "title", value = "广告名称")
    private String title;

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


}
