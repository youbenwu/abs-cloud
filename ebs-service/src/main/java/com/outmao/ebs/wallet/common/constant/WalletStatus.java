package com.outmao.ebs.wallet.common.constant;

public enum WalletStatus {

    WALLET_STATUS_NORMAL(0, "正常"),
    WALLET_STATUS_DISABLE(1, "冻结"),
    WALLET_STATUS_NotOpen(2, "未开通"),
    WALLET_STATUS_NotAudit(3, "未审核"),
    WALLET_STATUS_InAudit(4, "审核中"),
    WALLET_STATUS_AuditFailure(5, "审核失败");

    private int status;

    private String statusRemark;

    private WalletStatus(int status, String statusRemark) {
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


    public static boolean isNotNormal(int status){
        return status!=WALLET_STATUS_NORMAL.getStatus();
    }

    public static boolean isNormal(int status){
        return status==WALLET_STATUS_NORMAL.getStatus();
    }


}
