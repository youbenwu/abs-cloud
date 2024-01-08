package com.outmao.ebs.sys.dto;

import lombok.Data;

@Data
public class SetFeedbackStatusDTO {

    private Long id;

    //0--未处理 1--已处理 2--忽略 3--正在处理
    private int status;

    private String statusRemark;

}
