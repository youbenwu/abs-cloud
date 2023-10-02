package com.outmao.ebs.mall.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.mall.product.entity.Product;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_StoreSku",uniqueConstraints = {
        @UniqueConstraint(columnNames = { "storeId", "skuId" }) })
public class StoreSku implements Serializable {

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
     * 商品
     *
     */
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId",updatable = false)
    private Store store;

    /**
     *
     * 商品
     *
     */
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId",updatable = false)
    private Product product;

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
