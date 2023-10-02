package com.outmao.ebs.mall.merchant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户佣金提现
 *
 * */
@Data
@Entity
@Table(name = "ebs_UserCommissionCash")
public class UserCommissionCash   implements Serializable {

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


    @Column(nullable = false)
    private Long merchantId;

    /**
     *
     * 经纪人ID 合伙人ID
     *
     */
    @Column(nullable = false)
    private Long targetId;

    /**
     *
     * 0--经纪人佣金 1--合伙人佣金
     *
     */
    private int type;

    /**
     * 用户
     */
    @Column(nullable = false)
    private Long userId;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "commissionId")
    private UserCommission commission;


    /**
     *
     *
     * 状态 0--未处理 1--处理中 2--已汇款 3--不通过
     *
     */
    private int status;


    /**
     *
     *
     * 状态备注
     *
     */
    private String statusRemark;


    /**
     *
     *
     * 提现佣金
     *
     */
    private double amount;

    /**
     *
     *
     * 开户银行
     *
     */
    private String bankName;

    /**
     *
     *
     * 开户名
     *
     */
    private String bankAccount;

    /**
     *
     *
     * 银行卡号
     *
     */
    private String bankCardNumber;


    /**
     *
     *
     * 备注
     *
     */
    private String remark;



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
