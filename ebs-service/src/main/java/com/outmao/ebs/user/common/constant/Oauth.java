package com.outmao.ebs.user.common.constant;


/**
 *
 * 认证方式
 *
 */
public enum Oauth {

    USERNAME("USERNAME","帐号密码"),
    PHONE("PHONE","手机验证码"),
    EMAIL("EMAIL","邮箱验证码"),
    WECHAT("WECHAT","微信验证");

    private String name;
    private String description;

    Oauth(String name, String description){
        this.name=name;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
