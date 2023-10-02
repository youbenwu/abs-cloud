package com.outmao.ebs.mall.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductAttributeGroupDTO {

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
     * 属性列表
     *
     */
    private List<ProductAttributeDTO> attributes;


}
