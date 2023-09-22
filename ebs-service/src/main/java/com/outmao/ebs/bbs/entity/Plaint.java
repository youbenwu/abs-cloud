package com.outmao.ebs.bbs.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 投诉
 * */
@ApiModel(value = "Plaint", description = "投诉信息")
@Data
@Entity
@Table(name = "bbs_Plaint")
public class Plaint implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 投诉用户ID
     */
    private Long userId;

    /**
     * 被投诉用户ID
     */
    private Long toId;

    /**
     *
     * 被投诉内容类型
     * Subject--投诉主题
     * Post--投诉帖子
     * Comment--投诉评论
     *
     */
    private String targetType;

    /**
     *
     * 被投诉内容ID
     *
     */
    private Long targetId;

    /**
     *
     * 被投诉主题关联内容的ID
     *
     */
    private Long itemId;

    /**
     *
     * 被投诉主题关联内容的类型
     *
     */
    private String itemType;

    /**
     * 0--未处理 1--处理中 2--已处理 3--忽略
     */
    private int status;

    /**
     * 状态备注
     */
    private String statusRemark;

    /**
     * 投诉人联系方式
     */
    private String contact;

    /**
     * 投诉内容问题
     */
    private String content;

    /**
     * 时间
     */
    private Date createTime;

    /**
     * 时间
     */
    private Date updateTime;


}
