package com.outmao.ebs.hotel.common.constant;



public enum HotelDeviceLeaseOrderStatus {

    //0--未托管 1--已托管 2--已发货 3--部份激活 4--已激活 5--订单取消
    NoDeploy(0, "未托管"),
    IsDeploy(1, "已托管"),
    IsSend(2, "已发货"),
    InActive(3, "部份激活"),
    IsActive(4, "已激活"),
    Closed(5, "订单取消");

    private int status;

    private String statusRemark;

    private HotelDeviceLeaseOrderStatus(int status, String statusRemark) {
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
