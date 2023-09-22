package com.outmao.ebs.user.common.constant;

public enum CertificationStatus {

    NotCertification(0, "未实名认证"),
    RealName(1, "已实名认证");

    private int status;

    private String statusRemark;

    CertificationStatus(int status, String statusRemark) {
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
