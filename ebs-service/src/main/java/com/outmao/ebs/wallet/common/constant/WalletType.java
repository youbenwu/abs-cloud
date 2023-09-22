package com.outmao.ebs.wallet.common.constant;


public enum WalletType {

    WALLET_TYPE_USER(0, "个人钱包"),
    WALLET_TYPE_MERCHANT(1, "商户钱包");

    private int type;

    private String describe;

    private WalletType(int type, String describe) {
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
