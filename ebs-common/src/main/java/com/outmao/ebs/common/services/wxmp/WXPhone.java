package com.outmao.ebs.common.services.wxmp;

import lombok.Data;

@Data
public class WXPhone {

    //phoneNumber	string	用户绑定的手机号（国外手机号会有区号）
    //purePhoneNumber	string	没有区号的手机号
    //countryCode	string	区号

    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;

}
