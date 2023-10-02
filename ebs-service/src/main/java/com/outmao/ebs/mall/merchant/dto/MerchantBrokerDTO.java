package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "MerchantBrokerDTO", description = "保存经纪人参数")
@Data
public class MerchantBrokerDTO {


    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 商家ID
     *
     */
    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;
    /**
     *
     * 用户
     *
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;


    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;


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

    /**
     *
     * 主营房源
     *
     */
    private String business;

    /**
     *
     * 服务佣金
     *
     */
    private String fees;


}
