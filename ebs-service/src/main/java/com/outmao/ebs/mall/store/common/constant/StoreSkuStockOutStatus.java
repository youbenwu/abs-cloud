package com.outmao.ebs.mall.store.common.constant;


/**
 *
 * 状态
 * 0--末确认 1--待出库 2--已出库 3--已取消
 *
 */
public enum StoreSkuStockOutStatus {

    WAIT_CONFIRM(0, "末确认"),
    WAIT_OUT(10, "待出库"),
    FINISHED(20, "已出库"),
    CLOSED(30, "已取消");

    private int status;

    private String statusRemark;

    private StoreSkuStockOutStatus(int status, String statusRemark) {
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
