package com.outmao.ebs.mall.order.common.constant;


/**
 *
 * 订单子状态
 * 10 待发货：商家未确认
 * 11 待发货：商家已确认
 * 12 待发货：部分发货
 *
 * 20 待签收：商家已发货
 *
 * 30 已完成：超时自动签收
 * 31 已完成：买家签收
 * 32 已完成：商家标记签收
 *
 * 40 已关闭：待付款超时取消
 * 41 已关闭：待付款买家取消
 * 42 已关闭：待付款卖家取消
 * 43 已关闭：全款退款关闭
 *
 *
 */
public enum OrderSubStatus {

    SUCCESSED_NO_CONFIRM(10, "商家未确认"),
    SUCCESSED_CONFIRM(10, "商家已确认"),

    DELIVERED(20, "商家已发货"),

    FINISHED_AUTO(30, "超时签收"),
    FINISHED_BUYER(31, "买家签收"),
    FINISHED_SELLER(32, "商家标记完成"),

    CLOSED_AUTO(40, "超时取消"),
    CLOSED_BUYER(41, "买家取消"),
    CLOSED_SELLER(42, "商家取消"),
    CLOSED_REFUND(43, "全款退款关闭");

    private int status;

    private String statusRemark;

    private OrderSubStatus(int status, String statusRemark) {
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
