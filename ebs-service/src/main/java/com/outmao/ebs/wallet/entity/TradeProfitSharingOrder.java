package com.outmao.ebs.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_TradeProfitSharingOrder")
public class TradeProfitSharingOrder  implements Serializable {

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
     * 交易ID
     */
    private Long tradeId;

    /**
     * 分帐单号
     */
    private String orderNo;


    private int status;


    private Date createTime;


    private Date updateTime;



}
