package com.outmao.ebs.mall.product.common.constant;

public enum SoldProductStatus {

    //商品状态(0正常，1已退货)
    Normal(0, "正常"),
    Return(1, "已退货");

    private int status;

    private String statusRemark;

    private SoldProductStatus(int status, String statusRemark) {
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
