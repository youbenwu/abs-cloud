package com.outmao.ebs.mall.product.dto;

import lombok.Data;

@Data
public class ProductSkuStockOutDTO {
    private Long skuId;
    private int quantity;
}
