package com.outmao.ebs.studio.dto;

import lombok.Data;


/**
 * 影视集对象
 */
@Data
public class MovieEpisodeDTO {


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



}
