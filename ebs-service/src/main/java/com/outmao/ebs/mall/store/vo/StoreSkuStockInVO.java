package com.outmao.ebs.mall.store.vo;


import lombok.Data;

import java.util.Date;

@Data
public class StoreSkuStockInVO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 门店ID
     *
     */
    private Long storeId;


    /**
     *
     * 物品批号
     *
     */
    private String batchNo;


    /**
     *
     * 入库数量
     *
     */
    private long stock;


    /**
     *
     * 明细
     *
     */
    private String details;

    /**
     *
     * 备注
     *
     */
    private String remark;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

}
