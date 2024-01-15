package com.outmao.ebs.studio.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 影视集对象
 */
@Data
public class MovieEpisodeVO {


    /**
     * 自动ID
     */
    private Long id;

    /**
     *
     * 所属影视ID
     *
     */
    private Long movieId;

    /**
     *
     * 评论主题ID
     *
     */
    private Long subjectId;

    /**
     *
     * 发布用户ID
     *
     */
    private Long userId;

    /**
     * 付费类型
     * 0--免费 1--会员 2--付费
     */
    private int feeType;

    /**
     *
     * 价格
     *
     */
    private double price;

    /**
     *
     * 第几集
     *
     */
    private int index;

    /**
     *
     * 名称
     *
     */
    private String name;

    /**
     *
     * 简介
     *
     */
    private String intro;

    /**
     *
     * 封面
     *
     */
    private String cover;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


    private List<VideoVO> videos;

    /**
     *
     * 是否可看视频
     *
     */
    private Boolean playable;


}
