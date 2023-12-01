package com.outmao.ebs.mall.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.Address;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 订单收货地址
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_OrderAddress")
public class OrderAddress extends Address implements Serializable {

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


    @Column(updatable = false)
    private Long orderId;

    /**
     *
     * Name
     * 联系人姓名
     *
     */
    private String name;

    /**
     * Telephone Number
     * 联系人电话号码
     *
     */
    private String phone;


}
