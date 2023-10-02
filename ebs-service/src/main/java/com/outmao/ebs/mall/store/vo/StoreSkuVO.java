package com.outmao.ebs.mall.store.vo;


import lombok.Data;

import java.util.Date;

@Data
public class StoreSkuVO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 商品
     *
     */
    private Long storeId;

    /**
     *
     * 商品
     *
     */
    private Long productId;

    /**
     *
     * SKU ID
     *
     */
    private Long skuId;

    /**
     *
     * SKU编码
     *
     */
    private String skuNo;

    /**
     *
     * SKU名称
     *
     */
    private String skuName;

    /**
     * 商品库存
     */
    private long stock;

    /**
     * 可用库存
     */
    private long availableStock;

    /**
     * 告警库存
     */
    private Long alarmStock;

    /**
     * 0--正常 1--SKU无效
     */
    private int status;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     */
    private Date updateTime;

}
