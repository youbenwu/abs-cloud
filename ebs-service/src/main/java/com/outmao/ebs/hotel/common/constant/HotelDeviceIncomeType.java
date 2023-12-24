package com.outmao.ebs.hotel.common.constant;

public enum HotelDeviceIncomeType {

    Order(0,"订单购买服务费"),
    Advert(1,"广告收益");

    private int type;
    private String description;

    HotelDeviceIncomeType(int type, String description){
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
