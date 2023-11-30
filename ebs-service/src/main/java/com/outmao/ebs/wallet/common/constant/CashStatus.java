package com.outmao.ebs.wallet.common.constant;

public enum CashStatus {

    WAIT_PAY(0, "等待支付"),
    SUCCEED(1, "申请提现"),
    FINISHED(2, "提现完成"),
    CLOSED(3, "提现取消");

    private int status;

    private String statusRemark;

    private CashStatus(int status, String statusRemark) {
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
