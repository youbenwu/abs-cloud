package com.outmao.ebs.qrCode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActivateQrCodeDTO {

    private String url;

    private String business;

    public ActivateQrCodeDTO(String url){
        this.url=url;
    }

}
