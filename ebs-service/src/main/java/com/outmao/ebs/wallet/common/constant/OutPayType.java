package com.outmao.ebs.wallet.common.constant;


public enum OutPayType {


    App(0, "APP支付"),
    H5(1, "H5支付"),
    Jsapi(2, "JSAPI支付"),
    Nativepay(3, "当面付");

    private int type;

    private String describe;

    private OutPayType(int type, String describe) {
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
