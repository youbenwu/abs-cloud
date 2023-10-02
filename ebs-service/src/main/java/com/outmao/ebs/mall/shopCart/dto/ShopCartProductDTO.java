package com.outmao.ebs.mall.shopCart.dto;

import lombok.Data;

@Data
public class ShopCartProductDTO {

    private Long id;

    private Long userId;

    private Long skuId;

    private int quantity;

}
