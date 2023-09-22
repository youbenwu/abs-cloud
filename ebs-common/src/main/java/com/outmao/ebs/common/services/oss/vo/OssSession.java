package com.outmao.ebs.common.services.oss.vo;

import lombok.Data;

/*
*
* oss 临时会话
*
* */
@Data
public class OssSession {


    private String expiration;

    private String accessKeyId;

    private String accessKeySecret;

    private String securityToken;

    private String requestId;

    private String uploadUrl;

    private String baseUrl;


}
