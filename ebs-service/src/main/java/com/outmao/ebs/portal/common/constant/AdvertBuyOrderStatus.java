package com.outmao.ebs.portal.common.constant;



public enum AdvertBuyOrderStatus {

    WaitPay(0, "待支付"),
    Finish(1, "已完成"),
    Cancel(2, "已取消");

    private int status;

    private String statusRemark;

    private AdvertBuyOrderStatus(int status, String statusRemark) {
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
