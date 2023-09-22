package com.outmao.ebs.sys.dto;

import lombok.Data;

@Data
public class LogDTO {


    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 用户帐号
     *
     */
    private String username;

    /**
     *
     * 管理员ID
     *
     */
    private Long adminId;

    /**
     *
     * 管理员角色
     *
     */
    private String role;


    /**
     *
     * IP
     *
     */
    private String ip;

    /**
     *
     * 操作日志
     *
     */
    private String message;



}
