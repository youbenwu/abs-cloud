package com.outmao.ebs.mall.shopCart.common.constant;

public enum ShopCartProductStatus {

    //状态(0 正常，1 sku异常，2 商品异常)
    NORMAL(0, "正常"),
    SKU(1, "sku异常"),
    PRODUCT(2, "商品异常");

    private int status;

    private String statusRemark;

    private ShopCartProductStatus(int status, String statusRemark) {
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
