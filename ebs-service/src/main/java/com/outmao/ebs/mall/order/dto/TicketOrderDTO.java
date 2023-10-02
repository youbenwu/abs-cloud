package com.outmao.ebs.mall.order.dto;


import lombok.Data;

@Data
public class TicketOrderDTO {

    private Long userId;

    /**
     *
     * 门票订单号
     *
     */
    private String orderNo;


    /**
     *
     * 景点ID
     *
     */
    private String scenicId;

    /**
     *
     * 景点名称
     *
     */
    private String scenicName;


    /**
     *
     * 产品ID
     *
     */
    private String productId;

    /**
     *
     * 产品名称
     *
     */
    private String productName;


    /**
     *
     * 预订产品份数
     *
     */
    private int quantity;

    /**
     *
     * 订单总金额
     *
     */
    private float amount;


    /**
     *
     * 取票人/游客 姓名
     *
     */
    private String name;

    /**
     *
     * 取票人/游客 手机号
     *
     */
    private String phone;

}
