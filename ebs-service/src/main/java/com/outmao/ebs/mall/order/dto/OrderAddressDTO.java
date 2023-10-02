package com.outmao.ebs.mall.order.dto;

import com.outmao.ebs.common.vo.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "OrderAddressDTO", description = "订单收货地址参数")
@Data
public class OrderAddressDTO extends Address {

    /**
     * Name
     * 联系人姓名
     *
     */
    @ApiModelProperty(name = "name", value = "联系人姓名")
    private String name;

    /**
     * Telephone Number
     * 联系人电话号码
     *
     */
    @ApiModelProperty(name = "phone", value = "联系人电话号码")
    private String phone;

    /**
     * Telephone Number
     * 联系人电话号码
     *
     */
    @ApiModelProperty(name = "phone2", value = "联系人电话号码")
    private String phone2;

}
