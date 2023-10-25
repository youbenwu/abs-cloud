package com.outmao.ebs.portal.dto;


import lombok.Data;

/**
 *
 * 前端数据大屏展示数据
 *
 */
@Data
public class DataStatsDTO  {


    /**
     *
     * ID
     *
     */
    private Long id;


    /**
     *
     * 分组
     *
     */
    private String group;

    /**
     *
     * 统计类型
     *
     */
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



}
