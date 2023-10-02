package com.outmao.ebs.mall.product.common.constant;

public enum ProductStatus {

    //商品状态(0未上架，1已上架，2已删除)
    NO_SELL(0, "未上架"),
    ON_SELL(1, "已上架"),
    DELETED(2, "已删除");

    private int status;

    private String statusRemark;

    private ProductStatus(int status, String statusRemark) {
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
