package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 广告投放信息
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Embeddable
public class AdvertBuyDisplay implements Serializable {

    @ApiModelProperty(name = "screens", value = "屏幕数量")
    @Column(name = "buy_display_screens")
    private long screens;

    @ApiModelProperty(name = "amount", value = "总金额")
    @Column(name = "buy_display_amount")
    private double amount;

    @ApiModelProperty(name = "price", value = "每屏每天单价")
    @Column(name = "buy_display_price")
    private double price;

    @ApiModelProperty(name = "startTime", value = "广告展示开始时间")
    @Column(name = "buy_display_start_time")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "广告展示结束时间")
    @Column(name = "buy_display_end_time")
    private Date endTime;


}
