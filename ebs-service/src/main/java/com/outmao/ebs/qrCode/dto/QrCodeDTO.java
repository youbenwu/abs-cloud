package com.outmao.ebs.qrCode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeDTO {

    private Long   id;

    //二维码的值
    private String code;

    //状态 0--未激活；1--已激活
    private int    status;

    //状态 0--未激活；1--已激活
    private String statusRemark;

    //自定义的码类型
    private String type;

    //绑定业务URL
    private String url;

    //绑定业务参数
    private String business;



}
