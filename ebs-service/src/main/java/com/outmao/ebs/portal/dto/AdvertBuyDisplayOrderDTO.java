package com.outmao.ebs.portal.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.TimeSpan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 广告投放记录
 *
 */
@ApiModel(value = "AdvertBuyDisplayOrderDTO", description = "广告投放订单")
@Data
public class AdvertBuyDisplayOrderDTO implements Serializable {


    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "screens", value = "屏幕数量")
    private long screens;

    @ApiModelProperty(name = "amount", value = "订单金额")
    private double amount;

    @ApiModelProperty(name = "times", value = "广告投放期限")
    private TimeSpan times;

    @ApiModelProperty(name = "startTime", value = "广告展示开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "广告展示结束时间")
    private Date endTime;



}
