package com.outmao.ebs.mall.order.common.constant;


/**
 *
 * 订单退款状态
 * 0 没退款
 * 1 退款中：用户发起退款申请进入该状态
 * 2 退款成功
 * 3 退款失败
 *
 */
public enum OrderRefundStatus {

    NO_REFUND(0, "没退款"),
    IN_REFUND(1, "退款中：用户发起退款申请进入该状态"),
    REFUND_SUCCESS(2, "退款成功"),
    REFUND_FAILURE(3, "退款失败");

    private int status;

    private String statusRemark;

    private OrderRefundStatus(int status, String statusRemark) {
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
