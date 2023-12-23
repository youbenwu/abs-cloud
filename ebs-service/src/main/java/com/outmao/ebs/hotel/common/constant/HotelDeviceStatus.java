package com.outmao.ebs.hotel.common.constant;



public enum HotelDeviceStatus {

    ///**
    //     * 0--未激活
    //     * 1--已激活
    //     * 2--待托管
    //     *
    //     */

    NoActive(0, "未激活"),
    Active(1, "已激活"),
    NoDeploy(2, "未托管"),
    Deploy(3, "已托管");

    private int status;

    private String statusRemark;

    private HotelDeviceStatus(int status, String statusRemark) {
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
