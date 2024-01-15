package com.outmao.ebs.portal.entity;


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
@ApiModel(value = "DataCityStats", description = "前端数据大屏展示数据按城市统计")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_DataCityStats")
public class DataCityStats implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 0--显示 1--隐藏
     *
     */
    private int status;


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


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     */
    private Date updateTime;


}
