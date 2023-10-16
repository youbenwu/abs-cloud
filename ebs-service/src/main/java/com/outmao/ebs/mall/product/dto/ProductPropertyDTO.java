package com.outmao.ebs.mall.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductPropertyDTO {

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
    private List<ProductPropertyItemDTO> items;

    /**
     *
     * 属性的值 List<ProductPropertyItem> JSON
     *
     */
    private String value;


    /**
     *
     *
     * 属性类型 单选属性  复选属性  唯一属性
     *
     *
     */
    private int type;


    public ProductPropertyItemDTO getItem(String itemValue){
        for(ProductPropertyItemDTO item: items){
            if(item.getValue().equals(itemValue))
                return item;
        }
        return null;
    }


}
