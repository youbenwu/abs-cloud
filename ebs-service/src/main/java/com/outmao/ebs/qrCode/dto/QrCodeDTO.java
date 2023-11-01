package com.outmao.ebs.qrCode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeDTO {

    private Long id;

    //二维码的值
    private String code;

    //状态 0--未激活；1--已激活
    private int status;

    //状态 0--未激活；1--已激活
    private String statusRemark;

    //URL
    private String url;


}
