package com.outmao.ebs.mall.store.dto;

import lombok.Data;

@Data
public class StoreProductDTO {

    private Long id;

    private Long storeId;

    private Long productId;

    private Long categoryId;

    public StoreProductDTO(Long storeId,Long productId){
        this.storeId=storeId;
        this.productId=productId;
    }

}
