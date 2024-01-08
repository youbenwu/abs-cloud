package com.outmao.ebs.wallet.common.constant;


public enum TradeType {


    Transfer(0, "转账"),
    Pay(1, "支付"),
    Recharge(2, "充值"),
    Cash(3, "提现");

    private int type;

    private String describe;

    private TradeType(int type, String describe) {
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
