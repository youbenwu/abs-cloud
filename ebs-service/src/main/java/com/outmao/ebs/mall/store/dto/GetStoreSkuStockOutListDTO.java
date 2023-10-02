package com.outmao.ebs.mall.store.dto;

import lombok.Data;

@Data
public class GetStoreSkuStockOutListDTO {

    private Long storeId;

    private Integer[] statusIn;


}
