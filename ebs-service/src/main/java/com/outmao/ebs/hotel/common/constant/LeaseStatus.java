package com.outmao.ebs.hotel.common.constant;



public enum LeaseStatus {

    NoLease(0, "未租赁"),
    LeaseIng(1, "已租赁"),
    LeaseExpire(2, "租赁过期");

    private int status;

    private String statusRemark;

    private LeaseStatus(int status, String statusRemark) {
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
