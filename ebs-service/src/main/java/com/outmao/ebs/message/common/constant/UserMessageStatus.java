package com.outmao.ebs.message.common.constant;

import lombok.Getter;

@Getter
public enum UserMessageStatus {

    Unread(0, "未读"),
    Read(1, "已读"),
    Deleted(2, "删除");

    private int status;
    private String statusRemark;

    UserMessageStatus(int status, String statusRemark) {
        this.status = status;
        this.statusRemark = statusRemark;
    }

}
