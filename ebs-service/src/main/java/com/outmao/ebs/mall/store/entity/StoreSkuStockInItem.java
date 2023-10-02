package com.outmao.ebs.mall.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_StoreSkuStockInItem")
public class StoreSkuStockInItem   implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 入库单ID
     *
     */
    @Column(updatable = false)
    private Long stockInId;

    /**
     *
     * 商品ID
     *
     */
    private Long productId;

    /**
     *
     * 商品名称
     *
     */
    private String productTitle;

    /**
     *
     * SKU ID
     *
     */
    private Long skuId;

    /**
     *
     * SKU名称
     *
     */
    private String skuName;

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



}
