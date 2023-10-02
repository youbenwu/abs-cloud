package com.outmao.ebs.mall.takeLook.dto;


import lombok.Data;


@Data
public class TakeLookProductDTO {


    private Long id;

    /**
     *
     * 商品ID
     *
     */
    private Long productId;

    /**
     *
     * 商品类型
     *
     */
    private int productType;

    /**
     *
     * 商品名称
     *
     */
    private String productTitle;

    /**
     *
     * 商品图片
     *
     */
    private String productImage;

    /**
     * 商品SKU ID
     */
    private Long skuId;

    /**
     * 商品SKU
     */
    private String skuName;

    /**
     * 商品价格
     */
    private double price;


}
