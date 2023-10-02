package com.outmao.ebs.mall.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 入库单
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_StoreSkuStockIn")
public class StoreSkuStockIn  implements Serializable {

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
     * 门店ID
     *
     */
    @Column(updatable = false)
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
     * 备注
     *
     */
    private String remark;


    /**
     *
     * 明细
     *
     */
    @Lob
    private String details;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;




}
