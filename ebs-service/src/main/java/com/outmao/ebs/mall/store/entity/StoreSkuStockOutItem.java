package com.outmao.ebs.mall.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 出库单
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_StoreSkuStockOutItem")
public class StoreSkuStockOutItem implements Serializable {

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
     * 出库单ID
     *
     */
    @Column(updatable = false)
    private Long stockOutId;

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
     * 出库数量
     *
     */
    private long stock;


}
