package com.outmao.ebs.mall.promotion.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_GiftsProductSku",uniqueConstraints = {
        @UniqueConstraint(columnNames = { "giftsId", "skuId" }) })
public class GiftsProductSku implements Serializable {

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
     * 活动ID
     *
     */
    @Column(updatable = false)
    private Long giftsId;

    /**
     *
     * 商品ID
     *
     */
    @Column(updatable = false)
    private Long productId;


    /**
     *
     * 商品SKU ID
     *
     */
    @Column(updatable = false)
    private Long skuId;

    /**
     *
     * 赠品数量
     *
     */
    private int quantity;


    /**
     * 创建时间
     */
    private Date createTime;


}
