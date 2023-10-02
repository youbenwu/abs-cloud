package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "MerchantVO", description = "商家信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SimpleMerchantVO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    /**
     *
     * 用户
     *
     */
    @ApiModelProperty(name = "userId", value = "用户")
    private Long userId;

    /**
     *
     * 店铺ID
     *
     */
    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;


    /**
     *
     * 商家类型 0--企业 1--个人
     *
     * */
    @ApiModelProperty(name = "type", value = "商家类型 0--企业 1--个人")
    private int type;

    /**
     *
     * 商家名称
     *
     * */
    @ApiModelProperty(name = "name", value = "商家名称")
    private String name;




}
