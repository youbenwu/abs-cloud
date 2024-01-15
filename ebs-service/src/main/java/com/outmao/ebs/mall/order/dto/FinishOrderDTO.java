package com.outmao.ebs.mall.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class FinishOrderDTO {

    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    private int subStatus;

}
