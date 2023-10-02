package com.outmao.ebs.mall.product.dto;

import lombok.Data;

@Data
public class ProductPropertyItemDTO {

    /**
     * 唯一不变标识
     *
     */
    private Long id;

    /**
     *
     * 属性的值
     *
     */
    private String value;

//    /**
//     *
//     * 用于计算的值
//     *
//     */
//    private String v;


}
