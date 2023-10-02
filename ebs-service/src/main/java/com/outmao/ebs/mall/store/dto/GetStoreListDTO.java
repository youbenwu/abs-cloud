package com.outmao.ebs.mall.store.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetStoreListDTO", description = "获取门店列表参数")
@Data
public class GetStoreListDTO  {

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "keyword", value = "关键字查找")
    private String keyword;

}
