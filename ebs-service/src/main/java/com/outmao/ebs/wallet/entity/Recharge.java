package com.outmao.ebs.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_Recharge")
public class Recharge implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    //订单号
    @Column(unique = true)
    private String rechargeNo;

    //充值交易号
    @Column(unique = true)
    private String rechargeTradeNo;


    //订单状态 0--未支付 1--已支付 2--交易完成 3--交易关闭
    private int status;

    //订单状态备注
    private String statusRemark;

    //需要充值的货币
    private String currencyId;

    //充值数量
    private long quantity;

    //需要支付的金额
    private long amount;

    //备注
    private String remark;

    //创建时间
    private Date createTime;

    //支付时间
    private Date successTime;

    //完成时间
    private Date finishTime;

    //关闭时间
    private Date closeTime;


}
