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

    @ApiModelProperty(name = "userId", value = "广告投放的用户ID")
    private Long userId;

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "sort", value = "排序")
    private int sort;

    @ApiModelProperty(name = "status", value = "0--未上架 1--已上架 2--已过期")
    private int status;

    @ApiModelProperty(name = "type", value = "广告类型 0--系统广告 1--企业广告 2--个人广告")
    private int type;

    @ApiModelProperty(name = "item", value = "绑定商品 item.id--商品ID item.type--Product")
    @Embedded
    private BindingItem item;

    @ApiModelProperty(name = "citys", value = "广告投放城市，多个用逗号隔开")
    private String citys;

    @ApiModelProperty(name = "advertType", value = "广告类型 CPM--普通图片视频广告 CPC--带广告链接 CPA--带二维码")
    private String advertType;

    @ApiModelProperty(name = "title", value = "广告名称")
    private String title;

    @ApiModelProperty(name = "subtitle", value = "广告副标题")
    private String subtitle;

    @ApiModelProperty(name = "image", value = "图片地址")
    private String image;

    @ApiModelProperty(name = "images", value = "多张图片用逗号隔开")
    private String images;

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

    @ApiModelProperty(name = "places", value = "广告投放场所（酒店）ID，多个用逗号隔开")
    private String places;


}
