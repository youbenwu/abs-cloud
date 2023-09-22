package com.outmao.ebs.message.common.constant;


import lombok.Getter;

@Getter
public enum SendType {

    MSG(0, "站内信"),
    EMAIL(1, "邮箱"),
    SMS(2, "短信"),
    PUSH(3, "推送"),
    MP(4, "小程序消息");

    private int type;
    private String desc;

    SendType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
