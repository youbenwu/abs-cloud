package com.outmao.ebs.mall.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 订单物流状态记录
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_OrderLogisticsStatus")
public class OrderLogisticsStatus implements Serializable {

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

    /**
     * 订单物流
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "logisticsId")
    private OrderLogistics logistics;

    /**
     *
     * 自定义物流状态
     *
     */
    private int status;

    /**
     *
     * 自定义物流状态
     *
     */
    private String statusRemark;

    /**
     * 订单创建时间
     */
    private Date createTime;


}
