package com.outmao.ebs.wallet.common.constant;


public enum BankAccountType {

    BANK_ACCOUNT_TYPE_USER(0, "个人帐号"),
    BANK_ACCOUNT_TYPE_MERCHANT(1, "公司账号");

    private int type;

    private String describe;

    private BankAccountType(int type, String describe) {
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
