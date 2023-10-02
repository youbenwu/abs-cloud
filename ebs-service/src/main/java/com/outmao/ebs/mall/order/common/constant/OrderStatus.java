package com.outmao.ebs.mall.order.common.constant;


/*
 *
 * 订单状态
 * 00 待付款：用户下单未付款状态
 * 10 待发货：用户付款商家未发货状态
 * 20 待签收：商家发货用户未签收状态
 * 30 已完成：用户签收交易完成状态
 * 40 已关闭：待付款超时、退款完成进入该状态
 *
 */
public enum OrderStatus {

    WAIT_PAY(0, "待付款"),
    SUCCESSED(10, "待发货"),
    DELIVERED(20, "待签收"),
    FINISHED(30, "已完成"),
    CLOSED(40, "已关闭");

    private int status;

    private String statusRemark;

    private OrderStatus(int status, String statusRemark) {
        this.status = status;
        this.statusRemark = statusRemark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark;
    }


}
