package com.outmao.ebs.portal.common.constant;


/*
 *
 * 广告状态
 * 0 未上架
 * 1 已上架
 * 2 已过期
 *
 */
public enum AdvertStatus {

    NotUp(0, "未上架"),
    Up(1, "已上架"),
    Expire(2, "已过期"),
    NoPay(3, "没支付");

    private int status;

    private String statusRemark;

    private AdvertStatus(int status, String statusRemark) {
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
