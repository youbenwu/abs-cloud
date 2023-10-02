package com.outmao.ebs.mall.shopCart.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ShopCartProductVO {

    private Long id;

    private Long productId;

    private String productTitle;

    private String productImage;

    private Long skuId;

    private String skuName;

    private double price;

    private int quantity;

    private long store;

    private int status;

    private Date createTime;
    private Date updateTime;

}
