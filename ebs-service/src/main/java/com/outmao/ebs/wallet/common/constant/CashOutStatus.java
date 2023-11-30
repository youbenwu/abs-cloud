package com.outmao.ebs.wallet.common.constant;

public enum CashOutStatus {



    //外部状态 0--未处理 1--已打款 2--打款中 3--打款出错
    UNKNOWN(0, "未处理"),
    SUCCEED(1, "成功"),
    DEALING(2, "处理中"),
    FAIL(3, "失败");

    private int status;

    private String statusRemark;

    private CashOutStatus(int status, String statusRemark) {
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
