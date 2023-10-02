package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "MerchantPartnerDTO", description = "保存商家合伙人信息")
@Data
public class MerchantPartnerDTO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     * 商家
     */
    @ApiModelProperty(name = "merchantId", value = "商家",required = true)
    private Long merchantId;

    /**
     * 用户
     */
    @ApiModelProperty(name = "userId", value = "用户",required = true)
    private Long userId;

    /**
     *
     * 经纪人
     *
     */
    @ApiModelProperty(name = "brokerId", value = "经纪人ID",required = true)
    private Long brokerId;

    /**
     *
     * 分类的父分类
     *
     */
    @ApiModelProperty(name = "parentId", value = "分类的父分类ID")
    private Long parentId;

    /**
     *
     * 头像
     *
     */
    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;
    /**
     *
     * 名称
     *
     */
    @ApiModelProperty(name = "name", value = "名称",required = true)
    private String name;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    /**
     *
     * 电子邮箱
     *
     */
    @ApiModelProperty(name = "email", value = "电子邮箱")
    private String email;



}
