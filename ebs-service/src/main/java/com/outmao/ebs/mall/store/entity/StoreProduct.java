package com.outmao.ebs.mall.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.data.entity.Category;
import com.outmao.ebs.mall.product.entity.Product;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 门店商品
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_StoreProduct",uniqueConstraints = {
        @UniqueConstraint(columnNames = { "storeId", "productId" }) })
public class StoreProduct   implements Serializable {

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


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    /**
     * 创建时间
     */
    private Date createTime;


}
