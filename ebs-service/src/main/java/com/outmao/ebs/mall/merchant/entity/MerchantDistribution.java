package com.outmao.ebs.mall.merchant.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 商家分销设置
 *
 * */
@Data
@Entity
@Table(name = "ebs_MerchantDistribution")
public class MerchantDistribution  implements Serializable {

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





}
