package com.outmao.ebs.mall.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.Address;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 产品所在地址
 *
 */
@Data
@Entity
@Table(name = "ebs_ProductAddress")
public class ProductAddress extends Address  implements Serializable {

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

//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "productId")
//    private Product product;
    @Column(unique = true)
    private Long productId;

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
