package com.outmao.ebs.mall.product.dto;

import com.outmao.ebs.common.vo.Address;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "ProductAddressDTO", description = "房屋地址/销售地址")
@Data
public class ProductAddressDTO extends Address {

    private Long id;


}
