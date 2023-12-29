package com.outmao.ebs.mall.distribution.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 分销设置
 *
 * */
@Data
@Entity
@Table(name = "ebs_DistributionConfig")
public class DistributionConfig implements Serializable {

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
     *
     * 商家ID
     *
     * */
    @Column(unique = true,nullable = false)
    private Long merchantId;

    /**
     *
     * 是否启用分销
     *
     * */
    private boolean enable;

    /**
     *
     * 商品交易实际金额的提成比率 0～1
     * 如果商品有设置则使用商品设置的
     *
     * */
    private double productCommission;

    /**
     *
     * 经纪人佣金提成 0～1
     * 指商品交易佣金中的比率
     *
     * */
    private double brokerCommission;

    /**
     *
     * 直接合伙人佣金提成 0～1
     * 指商品交易佣金中的比率
     *
     * */
    private double partnerCommission;

    /**
     *
     * 上级合伙人佣金提成 0～1
     * 指商品交易佣金中的比率
     *
     * */
    private double partnerParentCommission;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


}
