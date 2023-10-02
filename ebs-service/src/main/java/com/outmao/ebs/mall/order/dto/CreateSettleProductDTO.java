package com.outmao.ebs.mall.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateSettleProductDTO {

    private Long skuId;

    private int quantity;

}
