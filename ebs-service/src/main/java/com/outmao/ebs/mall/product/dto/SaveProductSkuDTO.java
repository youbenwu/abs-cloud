package com.outmao.ebs.mall.product.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class SaveProductSkuDTO {

    /**
     * 唯一不变标识
     *
     */
    private Long id;

    /**
     *
     * 商品ID
     *
     */
    private Long productId;

    /**
     *
     * 排序
     *
     */
    private int sort;

    /**
     *
     * SKU名称
     *
     */
    private String name;


    /**
     *
     * SKU编号
     *
     */
    private String skuNo;


    /**
     * 商品价格
     */
    private double price;


    /**
     * 商品库存
     */
    private long stock;






}
