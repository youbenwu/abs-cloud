package com.outmao.ebs.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_TradeProfitSharingReceiver")
public class TradeProfitSharingReceiver  implements Serializable {

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
     * 分账单号ID
     */
    private Long sharingId;

    /**
     * 交易号
     */
    private String tradeNo;


    /**
     * 目标钱包ID
     */
    private Long account;

    /**
     * 分账金额
     */
    private long amount;

    /**
     * 分账备注
     */
    private String remark;

    /**
     *
     * 自定义业务类型
     *
     */
    private int businessType;

    /**
     *
     * 自定义业务说明
     *
     */
    private String business;


}
