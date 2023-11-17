package com.outmao.ebs.common.configuration.constant;

public enum Status {

    //状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败 5--审核成功 6--欠费 100--已删除
    NORMAL(0, "正常"),
    DISABLE(1, "禁用"),
    NotAudit(2, "未审核"),
    InAudit(3, "审核中"),
    AuditFailure(4, "审核失败"),
    AuditSuccess(5, "审核成功"),
    ARREARS(6, "欠费"),
    DELETED(100, "已删除");


    private int status;

    private String statusRemark;

    private Status(int status, String statusRemark) {
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
