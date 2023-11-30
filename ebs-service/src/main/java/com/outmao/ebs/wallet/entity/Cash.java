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
@Table(name = "ew_Cash")
public class Cash implements Serializable {

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

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    //订单号
    @Column(unique = true)
    private String orderNo;

    //订单状态 0--未支付 1--已支付 2--交易完成 3--交易关闭
    private int status;

    //订单状态备注
    private String statusRemark;

    //外部状态 0--未处理 1--已打款 2--打款中 3--打款出错
    private int outStatus;

    //外部状态
    private String outStatusRemark;

    //支付宝帐号
    @Embedded
    private CashAlipayAccount alipayAccount;

    //提现到银行卡
    @Embedded
    private CashBankAccount bankAccount;

    //支出的金额
    private double totalAmount;

    //实际拿到的金额
    private double amount;

    //手续费
    private double fee;

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
