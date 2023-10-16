package com.outmao.ebs.mall.product.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductAttributeDTO {

    /**
     * 唯一不变标识
     *
     */
    private Long id;

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
    private String value;

    /**
     *
     * 显示后缀
     *
     */
    private String suffix;


}
