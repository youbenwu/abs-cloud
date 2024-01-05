package com.outmao.ebs.hotel.common.constant;

public enum HotelDeviceIncomeType {

    HotelService(10,"客房服务"),
    AdvertCPM(20,"广告爆光"),
    AdvertCPS(21,"广告转化"),
    AdvertCPA(22,"广告分佣"),
    Vod(30,"影视点播"),
    Tour(40,"系统旅游订阅");

    private int type;
    private String description;

    public static HotelDeviceIncomeType get(int type){
        switch (type){
            case 10:
                return HotelService;
            case 20:
                return AdvertCPM;
            case 21:
                return AdvertCPS;
            case 22:
                return AdvertCPA;
            case 30:
                return Vod;
            case 40:
                return Tour;
        }
        return HotelService;
    }

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
