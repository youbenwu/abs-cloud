package com.outmao.ebs.mall.product.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 已售商品
 *
 * */
@Data
@Entity
@Table(name = "ebs_SoldProduct")
public class SoldProduct  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 分类唯一不变标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false,nullable = false)
    private Long shopId;

    @Column(updatable = false,nullable = false)
    private Long userId;

    @Column(updatable = false,nullable = false)
    private Long orderId;

    @Column(updatable = false,nullable = false)
    private Long productId;

    private int productType;

    private String productTitle;

    private String productImage;

    @Column(updatable = false,nullable = false)
    private Long skuId;

    private String skuName;

    private double price;

    private int quantity;

    private int status;

    private String statusRemark;

    private Date createTime;

    private Date updateTime;


}
