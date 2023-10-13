package com.outmao.ebs.mall.product.common.constant;


public enum ProductType {


    GENERAL(0, "普通商品"),
    VIRTUAL(1, "虚拟商品"),

    HOUSE_NEW(11, "新楼盘"),
    HOUSE_OLD(12, "二手房"),
    HOUSE_RENT(13, "出租房"),

    ADVERT_CHANNEL(20, "广告位");

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
