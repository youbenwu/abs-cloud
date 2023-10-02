package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "MerchantCustomerDTO", description = "保存商家客户参数")
@Data
public class MerchantCustomerDTO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     * 商家ID
     */
    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    /**
     * 客户用户ID
     */
    @ApiModelProperty(name = "userId", value = "客户用户ID")
    private Long userId;


    /**
     *
     * 经纪人ID
     *
     */
    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;


    /**
     *
     * 发展这个客户的合伙人ID
     *
     */
    @ApiModelProperty(name = "partnerId", value = "发展这个客户的合伙人ID")
    private Long partnerId;

    /**
     *
     * 名称
     *
     */
    @ApiModelProperty(name = "name", value = "名称")
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
     * 备注
     *
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;


}
