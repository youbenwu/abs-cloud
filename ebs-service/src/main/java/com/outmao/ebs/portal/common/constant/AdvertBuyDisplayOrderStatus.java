package com.outmao.ebs.portal.common.constant;


/*
 *
 * 0--没支付 1--待审核 2--已投放 3--已取消
 *
 */
public enum AdvertBuyDisplayOrderStatus {

    NoPay(0, "没支付"),
    WaitAudit(1, "待审核"),
    Up(2, "已投放"),
    Cancel(3, "已取消");

    private int status;

    private String statusRemark;

    private AdvertBuyDisplayOrderStatus(int status, String statusRemark) {
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
