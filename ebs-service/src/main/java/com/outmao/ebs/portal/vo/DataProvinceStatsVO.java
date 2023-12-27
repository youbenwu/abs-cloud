package com.outmao.ebs.portal.vo;

import lombok.Data;

@Data
public class DataProvinceStatsVO {

    /**
     *
     * 省份
     *
     */
    private String province;


    /**
     *
     * 数量
     *
     */
    private long count;

    /**
     *
     * 金额
     *
     */
    private double amount;


}
