package com.outmao.ebs.mall.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class SettleShopDTO {

    private Long shopId;

    private List<SettleProductDTO> products;


}
