package com.outmao.ebs.wallet.common.constant;


public enum OutPayType {


    WxPayApp(1, "微信APP支付"),
    WxPayH5(2, "微信H5支付"),
    WxPayJsapi(3, "微信JSAPI支付"),
    WxPayNativepay(4, "微信当面付");

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
