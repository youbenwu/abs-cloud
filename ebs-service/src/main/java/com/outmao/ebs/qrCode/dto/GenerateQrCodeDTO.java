package com.outmao.ebs.qrCode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateQrCodeDTO {

    private String code;
    private int width;
    private int height;
    private String logo;
    private String logoBorderColor;
    private Integer margin;

    public GenerateQrCodeDTO(String code, int width, int height){
        this.code=code;
        this.width=width;
        this.height=height;
    }




}
