package com.outmao.ebs.sys.dto;


import lombok.Data;

@Data
public class SysDTO {

    private Long id;

    /**
     * 系统类型，和Org中type对应
     */
    private int type;

    /**
     * 系统编号
     */
    private String sysNo;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 系统LOGO
     */
    private String logo;


}
