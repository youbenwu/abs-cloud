package com.outmao.ebs.hotel.common.constant;



public enum HotelRoomStatus {

    Idle(0, "空闲"),
    Stay(1, "入住"),
    Repair(2, "维修");

    private int status;

    private String statusRemark;

    private HotelRoomStatus(int status, String statusRemark) {
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
