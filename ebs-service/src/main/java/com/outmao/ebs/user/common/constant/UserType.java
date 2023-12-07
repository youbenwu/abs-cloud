package com.outmao.ebs.user.common.constant;


public enum UserType {

    User(0,"用户"),
    Robot(1,"机器人"),
    QyHotelDevice(20,"迁眼酒店设备");

    private int type;
    private String description;

    UserType(int type, String description){
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
