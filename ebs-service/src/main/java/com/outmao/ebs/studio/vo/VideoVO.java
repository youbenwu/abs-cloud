package com.outmao.ebs.studio.vo;


import lombok.Data;

import java.util.Date;

@Data
public class VideoVO {

    private Long id;

    private Long movieId;

    private Long episodeId;

    private String name;

    private String url;

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


}
