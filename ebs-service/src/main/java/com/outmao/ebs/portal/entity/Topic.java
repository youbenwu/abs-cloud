package com.outmao.ebs.portal.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 文章专题
 *
 */
@Data
@Entity
@Table(name = "portal_Topic")
public class Topic implements Serializable {

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
     * 组织ID
     *
     */
    @Column(nullable = false)
    private Long orgId;


    /**
     *
     * 用户ID
     *
     */
    @Column(nullable = false)
    private Long userId;


    /**
     *
     * 标题
     *
     */
    @Column(nullable = false)
    private String title;

    /**
     *
     * 详情
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
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
