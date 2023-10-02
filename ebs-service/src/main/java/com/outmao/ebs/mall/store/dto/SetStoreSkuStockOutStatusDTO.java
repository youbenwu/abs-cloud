package com.outmao.ebs.mall.store.dto;

import lombok.Data;

@Data
public class SetStoreSkuStockOutStatusDTO {

    private Long id;

    private Long orderId;

    /**
     *
     * 状态
     * 0--末确认 1--待出库 2--已出库 3--已取消
     *
     */
    private int status;

    /**
     *
     * 状态
     *
     */
    private String statusRemark;

}
