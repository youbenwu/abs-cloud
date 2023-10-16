package com.outmao.ebs.mall.product.common.constant;

public enum ProductSalesStatus {

    //销售状态（0待售/1在售/2售罄）
    FOR_SELL(0, "0待售"),
    ON_SELL(1, "1在售"),
    SELL_OUT(2, "2售罄");

    private int status;

    private String statusRemark;

    private ProductSalesStatus(int status, String statusRemark) {
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
