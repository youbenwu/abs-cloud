package com.outmao.ebs.mall.merchant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 *
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_MerchantPartnerStats")
public class MerchantPartnerStats implements Serializable {

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
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "partnerId")
    private MerchantPartner partner;


    @ApiModelProperty(name = "customerCount", value = "一级客户数")
    private long customerCount;

    @ApiModelProperty(name = "childrenCustomerCount", value = "二级客户数")
    private long childrenCustomerCount;

    @ApiModelProperty(name = "orderCount", value = "一级订单数")
    private long orderCount;

    @ApiModelProperty(name = "childrenOrderCount", value = "二级订单数")
    private long childrenOrderCount;

    @ApiModelProperty(name = "orderAmount", value = "一级订单金额")
    private long orderAmount;

    @ApiModelProperty(name = "childrenOrderAmount", value = "二级订单金额")
    private long childrenOrderAmount;


}
