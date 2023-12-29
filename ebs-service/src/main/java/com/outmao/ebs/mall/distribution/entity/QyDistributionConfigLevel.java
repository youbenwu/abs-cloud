package com.outmao.ebs.mall.distribution.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * 迁眼平台分销设置
 *
 * */
@Data
@Embeddable
public class QyDistributionConfigLevel implements Serializable {


    //4个等级
    //设备数量
    private int deviceNumberFrom;

    private int deviceNumberTo;

    //设备租赁推广收益
    private double partnerAmount;


}
