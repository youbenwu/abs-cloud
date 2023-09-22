package com.outmao.ebs.user.dto;

import lombok.Data;

@Data
public class UserDataDTO {


    private Long id;

    private Long userId;

    /**
     *
     * 类型
     *
     */
    private String type;


    /**
     *
     * 数据项名称
     *
     */
    private String name;

    /**
     *
     * 数据内容
     *
     */
    private String value;

    /**
     *
     * 是否选中
     *
     */
    private Boolean selected;

    /**
     *
     * 备注
     *
     */
    private String remark;

}
