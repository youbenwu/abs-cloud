package com.outmao.ebs.mall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSkuPropertyDTO {

//    /**
//     * 唯一不变标识
//     *
//     */
//    private Long id;

    /**
     *
     * 属性KEY
     *
     */
    private String key;

    /**
     *
     * 属性名称
     *
     */
    private String name;

    /**
     *
     * 属性的值
     *
     */
    private ProductSkuPropertyItemDTO value;


}
