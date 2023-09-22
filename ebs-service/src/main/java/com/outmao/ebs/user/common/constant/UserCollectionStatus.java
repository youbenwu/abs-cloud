package com.outmao.ebs.user.common.constant;

public enum UserCollectionStatus {

    NotCollect(0, "未收藏"),
    Collected(1, "已收藏");

    private int status;

    private String statusRemark;

    UserCollectionStatus(int status, String statusRemark) {
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
