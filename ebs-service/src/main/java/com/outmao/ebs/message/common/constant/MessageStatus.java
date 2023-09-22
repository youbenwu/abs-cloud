package com.outmao.ebs.message.common.constant;

import lombok.Getter;

@Getter
public enum MessageStatus {

    Unsent(0, "未发送"),
    Sending(1, "发送中"),
    Sended(2, "已发送"),
    SendFail(3, "发送失败"),
    Deleted(4, "己删除");

    private int status;
    private String statusRemark;

    MessageStatus(int status, String statusRemark) {
        this.status = status;
        this.statusRemark = statusRemark;
    }


}
