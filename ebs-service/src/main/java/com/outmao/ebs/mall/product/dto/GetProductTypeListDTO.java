package com.outmao.ebs.mall.product.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "GetProductTypeListDTO", description = "获取商品类型列表参数对象")
@Data
public class GetProductTypeListDTO extends BaseDTO {


    @ApiModelProperty(name = "type", value = "商品类型")
    private Integer type;


    @ApiModelProperty(name = "keyword", value = "关健字")
    private String keyword;


}
