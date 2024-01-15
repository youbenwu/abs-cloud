package com.outmao.ebs.studio.vo;


import lombok.Data;
import java.util.Date;


/**
 * 影视公司
 */
@Data
public class StudioVO{


    private Long id;

    /**
     * 组织ID
     */
    private Long orgId;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;




}
