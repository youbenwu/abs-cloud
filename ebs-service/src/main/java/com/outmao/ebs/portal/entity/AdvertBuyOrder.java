package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@ApiModel(value = "AdvertBuyOrder", description = "广告投放订单记录")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_AdvertBuyOrder")
public class AdvertBuyOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(name = "status", value = "0--待支付 1--已完成 2--已取消")
    private int status;

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

    private Date createTime;

    private Date updateTime;


}
