package com.outmao.ebs.mall.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class ToOrderShopDTO {

    private Long shopId;

    private String remark;

    private List<ToOrderProductDTO> products;

    public ToOrderProductDTO getProductBySkuId(Long skuId){
        for(ToOrderProductDTO p:products){
            if(p.getSkuId().equals(skuId))
                return p;
        }
        return null;
    }

}
