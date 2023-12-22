package com.outmao.ebs.user.common.constant;


public enum UserActiveType {

    //100--迁眼酒店设备每小时调一次
    //101--迁眼酒店设备开机
    //102--迁眼酒店设备关机
    QyHotelDeviceActive(100,"迁眼酒店设备每小时调一次"),
    QyHotelDeviceOn(101,"迁眼酒店设备开机"),
    QyHotelDeviceOff(102,"迁眼酒店设备关机");

    private int type;
    private String description;

    UserActiveType(int type, String description){
        this.type=type;
        this.description=description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
