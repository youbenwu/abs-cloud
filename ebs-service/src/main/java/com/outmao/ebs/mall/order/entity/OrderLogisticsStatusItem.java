package com.outmao.ebs.mall.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 订单物流跟踪
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_OrderLogisticsStatusItem")
public class OrderLogisticsStatusItem implements Serializable {
    /**
     *
     *
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 物流ID
     *
     */
    private Long logisticsId;

    /**
     *
     * 物流状态ID
     *
     */
    private Long statusId;

    /**
     *
     * 跟踪信息
     *
     */
    private String content;

    /**
     * 订单创建时间
     */
    private Date createTime;


}
