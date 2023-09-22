package com.outmao.ebs.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetAuthenticatedDTO {


    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 前端类型
     *
     */
    private String clientType;

    /**
     *
     * 授权ID
     *
     */
    private Long oauthId;

    /**
     *
     * 设备标识
     *
     */
    private String imei;

    /**
     *
     * 第三方会话KEY
     *
     */
    private String sessionKey;

    /**
     *
     * 会话过期时间
     *
     */
    private LocalDateTime expireTime;




}
