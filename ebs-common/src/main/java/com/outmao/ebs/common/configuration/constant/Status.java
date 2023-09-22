package com.outmao.ebs.common.configuration.constant;

public enum Status {

    //状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--已删除 7--欠费
    NORMAL(0, "正常"),
    DISABLE(1, "禁用"),
    NotAudit(2, "未审核"),
    InAudit(3, "审核中"),
    AuditSuccess(4, "审核成功"),
    AuditFailure(5, "审核失败"),
    DELETED(6, "已删除"),
    ARREARS(7, "欠费");


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
