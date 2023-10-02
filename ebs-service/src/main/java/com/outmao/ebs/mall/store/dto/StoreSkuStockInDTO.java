package com.outmao.ebs.mall.store.dto;

import lombok.Data;

import java.util.List;

@Data
public class StoreSkuStockInDTO {

    /**
     *
     * 物品批号
     *
     */
    private String batchNo;

    /**
     *
     * 门店ID
     *
     */
    private Long storeId;

    /**
     *
     * 明细
     *
     */
    private List<StoreSkuStockInItemDTO> items;


    /**
     *
     * 备注
     *
     */
    private String remark;

}
