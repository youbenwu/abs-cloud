package com.outmao.ebs.mall.order.dto;

import com.outmao.ebs.common.vo.LogisticsInfo;
import com.outmao.ebs.common.vo.SimpleContact;
import lombok.Data;

@Data
public class OrderLogisticsDTO {

    /**
     * 自动编号
     */
    private Long id;

    /**
     * 订单
     */
    private Long  orderId;

    /**
     *
     * 发货地址
     *
     */
    private SimpleContact from;

    /**
     *
     * 收货地址
     *
     */
    private SimpleContact to;

    /**
     *
     * 物流信息
     *
     */
    private LogisticsInfo logisticsInfo;



}
