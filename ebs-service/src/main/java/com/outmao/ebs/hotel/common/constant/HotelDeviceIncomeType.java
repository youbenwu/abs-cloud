package com.outmao.ebs.hotel.common.constant;

public enum HotelDeviceIncomeType {

    HotelService(10,"客房服务"),
    AdvertV(20,"广告爆光"),
    AdvertPv(21,"广告转化"),
    Vod(30,"影视点播"),
    Tour(40,"系统旅游订阅");

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
