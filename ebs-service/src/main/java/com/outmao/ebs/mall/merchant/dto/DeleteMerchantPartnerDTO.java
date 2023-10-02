package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "DeleteMerchantPartnerDTO", description = "删除商家合伙人信息")
@Data
public class DeleteMerchantPartnerDTO {

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



}
