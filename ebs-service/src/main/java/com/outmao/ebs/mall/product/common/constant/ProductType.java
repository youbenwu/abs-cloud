package com.outmao.ebs.mall.product.common.constant;


/**
 *
 * 商品类型  0--普通商品 11--新楼盘 12--二手房 13--出租房 1--虚拟商品 20--广告频道 30--酒店服务 100--外部携程旅游商品
 *
 * **/

public enum ProductType {


    GENERAL(0, "普通商品"),
    VIRTUAL(1, "虚拟商品"),

    HOUSE_NEW(11, "新楼盘"),
    HOUSE_OLD(12, "二手房"),
    HOUSE_RENT(13, "出租房"),

    ADVERT_CHANNEL(20, "广告频道"),

    HOTEL_WASH_SERVICE(30, "酒店干洗服务"),
    HOTEL_FOOD_SERVICE(31, "酒店送餐服务"),
    HOTEL_MALL(32, "酒店商超"),

    HOTEL_DEVICE_LEASE(40, "酒店设备租赁"),
    HOTEL_DEVICE(41, "酒店设备购买"),


    OUT_CTRIP(100, "外部携程旅游商品");

    private int type;

    private String describe;

    private ProductType(int type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
