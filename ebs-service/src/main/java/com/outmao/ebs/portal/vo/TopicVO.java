package com.outmao.ebs.portal.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TopicVO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    private Long orgId;

    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 标题
     *
     */
    private String title;

    /**
     *
     * 详情
     *
     */
    private String content;

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
