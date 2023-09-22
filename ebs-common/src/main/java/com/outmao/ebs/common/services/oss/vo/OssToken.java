package com.outmao.ebs.common.services.oss.vo;

import lombok.Data;

/*
*
* oss 临时会话
*
* System.out.println("accessKeyId："+accessId);
        System.out.println("encodedPolicy："+encodedPolicy);
        System.out.println("postSignature："+postSignature);
        System.out.println("dir："+dir);
        System.out.println("host："+host);
        System.out.println("expire："+(expireEndTime / 1000));
*
* */
@Data
public class OssToken {


    private String accessKeyId;

    private String encodedPolicy;

    private String postSignature;

    private String dir;

    private String host;

    private Long expire;


}
