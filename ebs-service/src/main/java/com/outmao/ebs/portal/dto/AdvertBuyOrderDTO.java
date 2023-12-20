package com.outmao.ebs.portal.dto;


import com.outmao.ebs.common.vo.TimeSpan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 广告投放记录
 *
 */
@ApiModel(value = "AdvertBuyOrderDTO", description = "广告投放订单")
@Data
public class AdvertBuyOrderDTO implements Serializable {


    @ApiModelProperty(name = "orderNo", value = "订单号")
    @Column(unique = true)
    private String orderNo;

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "pv", value = "购买PV数量")
    private long pv;

    @ApiModelProperty(name = "price", value = "PV单价")
    private double price;

    @ApiModelProperty(name = "amount", value = "订单金额")
    private double amount;


}
