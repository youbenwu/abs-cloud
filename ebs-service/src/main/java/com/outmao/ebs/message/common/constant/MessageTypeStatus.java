package com.outmao.ebs.message.common.constant;

import lombok.Getter;

@Getter
public enum MessageTypeStatus {

    Normal(0, "正常"),
    Disable(1, "禁用");

    private int status;
    private String statusRemark;

    MessageTypeStatus(int status, String statusRemark) {
        this.status = status;
        this.statusRemark = statusRemark;
    }


}
