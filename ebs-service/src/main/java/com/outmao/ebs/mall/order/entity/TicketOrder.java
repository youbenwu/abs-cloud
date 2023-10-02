package com.outmao.ebs.mall.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/****
 *
 *
 *
 * 和磐河旅游开放平台对接的门票订单
 *
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_TicketOrder")
public class TicketOrder   implements Serializable {
    /**
     *
     *
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;


    @Column(nullable = false)
    private Long userId;


    /**
     *
     * 门票订单号
     *
     */
    @Column(unique = true,nullable = false)
    private String orderNo;


    /**
     *
     * 外部订单状态（待支付 = 1 ,出票中 = 2,已出票 = 3 ,出票失败 = 4 ,退订中 = 5 ,退订失败 = 6 ,已退订 = 7 , 已取消 = 99）
     *
     */
    private int outStatus;


    /**
     *
     * 订单状态
     * 00 待付款：用户下单未付款状态
     * 10 待出票：用户付款出票中状态
     * 20 已出票：已出票状态
     * 30 已完成：交易完成状态，不能退订
     * 40 已关闭：待付款超时、退款完成进入该状态
     *
     */
    private int status;

    /**
     *
     * 订单状态备注
     *
     */
    private String statusRemark;


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



    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


}
