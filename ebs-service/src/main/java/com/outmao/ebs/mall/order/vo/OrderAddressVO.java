package com.outmao.ebs.mall.order.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "OrderAddressVO", description = "订单收货地址")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class OrderAddressVO extends Address {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "name", value = "联系人姓名")
    private String name;

    @ApiModelProperty(name = "phone", value = "联系人电话号码")
    private String phone;

    @ApiModelProperty(name = "phone2", value = "联系人电话号码")
    private String phone2;

}
