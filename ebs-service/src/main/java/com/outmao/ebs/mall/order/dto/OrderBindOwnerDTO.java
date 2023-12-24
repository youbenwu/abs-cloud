package com.outmao.ebs.mall.order.dto;

import lombok.Data;

@Data
public class OrderBindOwnerDTO {

    private Long qrCodeId;

    private String orderNo;

    private Long userId;


    private String phone;


}
