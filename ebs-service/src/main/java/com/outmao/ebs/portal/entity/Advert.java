package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.common.vo.SortEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * 广告
 *
 */
@ApiModel(value = "Advert", description = "广告")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_Advert")
public class Advert extends SortEntity {


    @ApiModelProperty(name = "display", value = "是否上架")
    private boolean display;

    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败 7--过期 8--待订单完成 9--订单取消")
    private int status;

    @ApiModelProperty(name = "orgId", value = "发布广告的组织ID")
    private Long orgId;

    @ApiModelProperty(name = "userId", value = "广告投放的用户ID")
    private Long userId;

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "isPlace", value = "固定场所广告,只在特定场所显示")
    private boolean isPlace;

    @ApiModelProperty(name = "citys", value = "投放城市")
    private String citys;


    @ApiModelProperty(name = "type", value = "广告类型 0--平台广告 1--企业广告 2--个人广告")
    private int type;

    @ApiModelProperty(name = "item", value = "绑定商品 item.id--商品ID item.type--Product")
    @Embedded
    private BindingItem item;

    @ApiModelProperty(name = "advertType", value = "广告类型 CPM--普通图片视频广告 CPC--带广告链接 CPA--带二维码")
    private String advertType;

    @ApiModelProperty(name = "title", value = "广告标题")
    private String title;

    @ApiModelProperty(name = "subtitle", value = "广告副标题")
    private String subtitle;

    @ApiModelProperty(name = "image", value = "图片地址")
    private String image;

    @ApiModelProperty(name = "images", value = "多张图片用逗号隔开")
    private String images;

    @ApiModelProperty(name = "url", value = "广告跳转地址")
    private String url;

    @ApiModelProperty(name = "movie", value = "广告视频地址")
    private String video;

    @ApiModelProperty(name = "qrCode", value = "广告二维码地址")
    private String qrCode;

    @ApiModelProperty(name = "startTime", value = "广告展示开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "广告展示结束时间")
    private Date endTime;

    @Embedded
    private AdvertBuy buy;

    @Embedded
    private AdvertBuyDisplay buyDisplay;

    @ApiModelProperty(name = "pv", value = "流量")
    private long pv;

    @ApiModelProperty(name = "uv", value = "独立访客，一台电脑24小时以内访问N次计为1次")
    private long uv;


}
