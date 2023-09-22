package com.outmao.ebs.qrCode.dto;


import lombok.Data;

@Data
public class ActivateQrCodeDTO {

    private Long id;

    private String type;

    private String url;

    private String business;

}
