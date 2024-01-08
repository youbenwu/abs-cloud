package com.outmao.ebs.wallet.common.constant;

public enum TradeStatus {

    /**
     * TRADE_WAIT_PAY--等待支付
     * TRADE_SUCCEED--支付成功、转出到付款方帐号的预付款项，并不会直接转给收款方帐号
     * TRADE_FINISHED--交易完成、已转给收款方帐号
     * TRADE_CLOSED--交易关闭、超时关闭或全额退款
     */

    TRADE_WAIT_PAY(0, "等待支付"),
    TRADE_SUCCEED(1, "支付成功"),
    TRADE_FINISHED(2, "交易完成"),
    TRADE_CLOSED(3, "交易关闭");

    private int status;

    private String statusRemark;

    private TradeStatus(int status, String statusRemark) {
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
