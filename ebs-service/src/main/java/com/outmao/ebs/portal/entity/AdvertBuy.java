package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * 广告投放信息
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Embeddable
public class AdvertBuy implements Serializable {

    @ApiModelProperty(name = "pv", value = "广告主购买的PV数")
    @Column(name = "buy_pv")
    private long pv;

    @ApiModelProperty(name = "price", value = "广告主购买的每PV价钱")
    @Column(name = "buy_price")
    private double price;

    @ApiModelProperty(name = "amount", value = "广告主购买的总金额")
    @Column(name = "buy_amount")
    private double amount;


    public void newBuy(AdvertBuy buy){
        pv+=buy.getPv();
        amount+=buy.getAmount();
        price=buy.price;
    }


}
