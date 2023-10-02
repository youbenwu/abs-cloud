package com.outmao.ebs.mall.store.dto;

import lombok.Data;

@Data
public class StoreSkuStockInItemDTO {

    /**
     *
     * SKU ID
     *
     */
    private Long skuId;

    /**
     * 商品库存
     */
    private long stock;


}
