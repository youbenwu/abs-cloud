package com.outmao.ebs.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_TradeProfitSharing")
public class TradeProfitSharing implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 交易号
     */
    private String tradeNo;

    /**
     * 分帐单号
     */
    @Column(unique = true)
    private String sharingNo;

    /**
     *
     * 状态 1--处理中 2--分帐完成
     *
     */
    private int status;


    /**
     *
     * 是否解冻商户金额
     *
     */
    private boolean unfreeze;

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
