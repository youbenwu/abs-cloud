package com.outmao.ebs.mall.shopCart.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class DeleteShopCartProductListDTO {

    private Long userId;

    private Collection<Long> skuIdIn;

}
