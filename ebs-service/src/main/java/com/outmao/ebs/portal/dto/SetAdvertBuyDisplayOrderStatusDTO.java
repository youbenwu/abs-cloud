package com.outmao.ebs.portal.dto;


import com.outmao.ebs.common.vo.TimeSpan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 广告投放记录
 *
 */
@ApiModel(value = "SetAdvertBuyDisplayOrderStatusDTO", description = "设置状态")
@Data
public class SetAdvertBuyDisplayOrderStatusDTO implements Serializable {


    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    @ApiModelProperty(name = "status", value = "状态")
    private int status;




}
