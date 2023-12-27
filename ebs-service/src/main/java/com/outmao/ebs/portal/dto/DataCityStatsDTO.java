package com.outmao.ebs.portal.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 前端数据大屏展示数据按城市统计
 *
 */

@Data
public class DataCityStatsDTO  {


    private Long id;


    /**
     *
     * 统计类型 100--酒店设备统计
     *
     */
    private Integer type;

    /**
     *
     * 省份
     *
     */
    private String province;


    /**
     *
     * 城市
     *
     */
    private String city;


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
