package com.outmao.ebs.mall.order.dto;

import lombok.Data;


@Data
public class UpdateSettleDTO {

    private Long id;

    private Long addressId;

    private String payChannel;


}
