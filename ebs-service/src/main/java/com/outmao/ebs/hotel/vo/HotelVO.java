package com.outmao.ebs.hotel.vo;

import com.outmao.ebs.hotel.entity.HotelContact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@ApiModel(value = "HotelVO", description = "酒店信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelVO {

    @ApiModelProperty(name = "id", value = "酒店ID")
    private Long id;

    /**
     *
     * 酒店所有者用户ID
     *
     */
    @ApiModelProperty(name = "userId", value = "酒店所有者用户ID")
    private Long userId;

    /**
     * 组织ID
     */
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    /**
     * 商家ID
     */
    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    /**
     * 店铺ID
     */
    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;


    /**
     *
     * 酒店状态 0--正常 1--禁用 2--未审核 5--审核失败 7--欠费
     *
     */
    @ApiModelProperty(name = "status", value = "酒店状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败")
    private int status;

    /**
     *
     * 酒店状态备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "酒店状态备注")
    private String statusRemark;



    /**
     *
     * 酒店名称
     *
     */
    @ApiModelProperty(name = "name", value = "酒店名称")
    private String name;

    /**
     *
     * 酒店简介
     *
     */
    @ApiModelProperty(name = "intro", value = "酒店简介")
    private String intro;

    /**
     *
     * 酒店LOGO
     *
     */
    @ApiModelProperty(name = "logo", value = "酒店LOGO")
    private String logo;

    /**
     *
     * 酒店图片
     *
     */
    @ApiModelProperty(name = "image", value = "酒店图片")
    private String image;

    /**
     *
     * 营业执照
     *
     */
    @ApiModelProperty(name = "license", value = "营业执照")
    private String license;

    /**
     *
     * 身份证号码
     *
     */
    @ApiModelProperty(name = "idCardNo", value = "身份证号码")
    private String idCardNo;

    /**
     *
     * 身份证正面
     *
     */
    @ApiModelProperty(name = "idCardFront", value = "身份证正面")
    private String idCardFront;

    /**
     *
     * 身份证反面
     *
     */
    @ApiModelProperty(name = "idCardBack", value = "身份证反面")
    private String idCardBack;


    /**
     *
     * 联系信息
     *
     */
    @ApiModelProperty(name = "contact", value = "联系信息")
    private HotelContact contact;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;



}
