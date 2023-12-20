package com.outmao.ebs.hotel.common.constant;



public enum HotelRoomDeviceStatus {

    //0--无设备 1--设备投放中 2--设备已投放
    NoDevice(0, "无设备"),
    PreDevice(1, "设备投放中"),
    HasDevice(2, "设备已投放");

    private int status;

    private String statusRemark;

    private HotelRoomDeviceStatus(int status, String statusRemark) {
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
