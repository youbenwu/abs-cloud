package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetMerchantListDTO", description = "获取商家列表参数")
@Data
public class GetMerchantListDTO {

    @ApiModelProperty(name = "keyword", value = "搜索关键字")
    private String keyword;


}
