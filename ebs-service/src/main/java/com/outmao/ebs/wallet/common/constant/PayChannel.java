package com.outmao.ebs.wallet.common.constant;


public enum PayChannel {

    /**
     * WalletPay--钱包支付
     * AliPay--支付宝支付
     * WxPay--微信支付
     * UnionPay--银联支付
     */
    WalletPay(0, "钱包支付"),
    AliPay(1, "支付宝支付"),
    WxPay(2, "微信支付"),
    UnionPay(3, "银联支付");

    private int type;

    private String describe;

    private PayChannel(int type, String describe) {
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
