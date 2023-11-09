package com.outmao.ebs.mall.product.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 商品服务
 *
 */
@Data
@Entity
@Table(name = "ebs_ProductServ")
public class ProductServ implements Serializable {

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



}
