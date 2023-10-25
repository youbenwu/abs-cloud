package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 前端数据大屏展示数据
 *
 */
@ApiModel(value = "DataStats", description = "前端数据大屏展示数据")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_DataStats")
public class DataStats  implements Serializable {

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
     * 分组
     *
     */
    private String channel;

    /**
     *
     * 统计类型
     *
     */
    @Column(unique = true)
    private Integer type;


    private String title;


    private String value;


    private String realValue;


    /**
     *
     * 显示后缀
     *
     */
    private String suffix;


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
