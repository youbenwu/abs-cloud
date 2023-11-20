package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.common.vo.SortEntity;
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
@ApiModel(value = "AdvertOrder", description = "广告投放记录")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_AdvertOrder")
public class AdvertOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 订单状态
     * 0 待付款：用户下单未付款状态
     * 1 已付款：用户付款商家未发货状态
     * 2 待签收：商家发货用户未签收状态
     * 3 已完成：用户签收交易完成状态
     * 4 已关闭：待付款超时、全款退款完成进入该状态
     *
     */
    private int status;


    @ApiModelProperty(name = "orderNo", value = "订单号")
    @Column(unique = true)
    private String orderNo;

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "pv", value = "购买流量")
    private long pv;

    @ApiModelProperty(name = "amount", value = "订单金额")
    private double amount;

    private Date createTime;


}
