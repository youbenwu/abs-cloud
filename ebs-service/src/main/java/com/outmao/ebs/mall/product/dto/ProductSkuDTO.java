package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProductSkuDTO {

    /**
     * 唯一不变标识
     *
     */
    private Long id;

    /**
     *
     * SKU名称 格式--规格1 规格2 规格3
     *
     */
    private String name;

    /**
     *
     * SKU标签
     *
     */
    @ApiModelProperty(name = "marks", value = "标签")
    private String marks;

    /**
     *
     * SKU编号
     *
     */
    private String skuNo;

    /**
     *
     * sku图片
     *
     */
    private String image;

    /**
     *
     * sku图片
     *
     */
    private List<ProductImageDTO> images;

    /**
     *
     * 规格值
     *
     */
    private List<ProductSkuPropertyDTO> value;

    /**
     * 商品价格
     */
    private double price;

    /**
     *
     * 单价（房屋销售里的每平方米单价）
     *
     */
    private Double unitPrice;

    /**
     * 商品库存
     */
    private long stock;

    /**
     * 告警库存
     */
    private Long alarmStock;


    public ProductSkuDTO(String name,double price, long stock){
        this.name=name;
        this.price=price;
        this.stock=stock;
    }


    public String getName() {
        if(name==null||name.isEmpty()){
            if(value!=null){
               StringBuffer sb=new StringBuffer();
                for (ProductSkuPropertyDTO p:value) {
                    sb.append(p.getValue().getValue()+" ");
                };
                name=sb.toString().trim();
            }
        }
        return name;
    }

}
