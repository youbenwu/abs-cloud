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


    @ApiModelProperty(name = "userId", value = "广告投放的用户ID")
    private Long userId;


    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;


    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;


    /**
     *
     * 0--未上架 1--已上架 2--需缴费
     *
     * */
    @ApiModelProperty(name = "status", value = "0--未上架 1--已上架 2--需缴费")
    private int status;

    /**
     *
     * 广告类型
     *
     * */
    @ApiModelProperty(name = "type", value = "广告类型 0--系统广告 1--企业广告 2--个人广告")
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


    @ApiModelProperty(name = "buyPv", value = "广告主购买的PV数")
    private long buyPv;

    @ApiModelProperty(name = "buyPrice", value = "广告主购买的每PV价钱")
    private double buyPrice;

    @ApiModelProperty(name = "buyAmount", value = "广告主购买的总金额")
    private double buyAmount;


    @ApiModelProperty(name = "pv", value = "流量")
    private long pv;

    @ApiModelProperty(name = "uv", value = "独立访客，一台电脑24小时以内访问N次计为1次")
    private long uv;





}
