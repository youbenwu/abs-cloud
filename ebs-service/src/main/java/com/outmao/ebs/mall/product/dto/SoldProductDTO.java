package com.outmao.ebs.mall.product.dto;

import lombok.Data;

@Data
public class SoldProductDTO {

    private Long shopId;

    private Long userId;

    private Long orderId;

    private Long productId;

    private int productType;

    private String productTitle;

    private String productImage;

    private Long skuId;

    private String skuName;

    private double price;

    private int quantity;


}
