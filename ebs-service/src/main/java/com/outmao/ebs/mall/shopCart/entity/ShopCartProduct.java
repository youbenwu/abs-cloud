package com.outmao.ebs.mall.shopCart.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_ShopCartProduct",uniqueConstraints = {
        @UniqueConstraint(columnNames = { "userId", "skuId" }) })
public class ShopCartProduct  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 唯一不变标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long shopId;

    private String shopTitle;

    private Long productId;

    private String productTitle;

    private String productImage;

    private Long skuId;

    private String skuName;

    private double price;

    private int quantity;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
