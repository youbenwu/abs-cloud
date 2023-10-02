package com.outmao.ebs.mall.shopCart.vo;

import lombok.Data;

import java.util.List;

@Data
public class ShopCartShopVO {

    private Long shopId;

    private String shopTitle;

    private List<ShopCartProductVO> products;

}
