package com.outmao.ebs.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * 用户活跃记录
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActiveDTO {


    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 用户活动类型
     *
     */
    private int type;

    /**
     *
     * 用户活动类型
     *
     */
    private String message;




}
