package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备购买信息
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Embeddable
public class HotelDeviceBuy implements Serializable {

    /**
     *
     * 设备所有者用户ID
     *
     */
    private Long ownerId;

    /**
     *
     * 合伙人ID
     *
     */
    private Long partnerId;

    /**
     *
     * 购买设备的金额
     *
     */
    private double amount;



}
