package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "DeleteMerchantCustomerDTO", description = "删除商家客户参数")
@Data
public class DeleteMerchantCustomerDTO {

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




}
