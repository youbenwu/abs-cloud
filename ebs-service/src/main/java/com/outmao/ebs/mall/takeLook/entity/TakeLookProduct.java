package com.outmao.ebs.mall.takeLook.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.mall.takeLook.entity.TakeLook;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 带看房源
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_TakeLookProduct")
public class TakeLookProduct implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "lookId")
    private TakeLook look;

    /**
     *
     * 商品类型
     *
     */
    private int productType;

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
     * 商品图片
     *
     */
    private String productImage;

    /**
     * 商品SKU ID
     */
    private Long skuId;

    /**
     * 商品SKU
     */
    private String skuName;

    /**
     * 商品价格
     */
    private double price;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;


}
