package com.outmao.ebs.mall.shopCart.vo;

import lombok.Data;

import java.util.List;

@Data
public class ShopCartVO {

    private Long userId;

    private List<ShopCartShopVO> shops;

}
