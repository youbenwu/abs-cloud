package com.outmao.ebs.qrCode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQrCodeListDTO {

    //状态 0--未激活；1--已激活
    private Integer status;


}
