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

    private Long tradeId;

    private Long orderId;

    private Long walletId;

    private long amount;

    private String description;

}
