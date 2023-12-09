package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.common.vo.SortEntity;
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
@ApiModel(value = "AdvertBuyDisplayOrder", description = "广告投放记录")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_AdvertBuyDisplayOrder")
public class AdvertBuyDisplayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(name = "status", value = "0--待支付 1--待审核 2--已投放 3--已取消")
    private int status;

    @ApiModelProperty(name = "orderNo", value = "订单号")
    @Column(unique = true)
    private String orderNo;

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "screens", value = "屏幕数量")
    private long screens;

    @ApiModelProperty(name = "price", value = "每屏每天单价")
    private double price;

    @ApiModelProperty(name = "amount", value = "订单金额")
    private double amount;

    @ApiModelProperty(name = "times", value = "广告投放期限")
    private TimeSpan times;

    @ApiModelProperty(name = "startTime", value = "广告展示开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "广告展示结束时间")
    private Date endTime;

    private Date createTime;

    private Date updateTime;


}
