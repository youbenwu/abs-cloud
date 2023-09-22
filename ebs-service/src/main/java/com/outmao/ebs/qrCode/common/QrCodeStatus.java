package com.outmao.ebs.qrCode.common;

public enum QrCodeStatus {

    NotActivated(0, "未激活"),
    Activated(1, "已激活");


    private int status;

    private String statusRemark;

    private QrCodeStatus(int status, String statusRemark) {
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
