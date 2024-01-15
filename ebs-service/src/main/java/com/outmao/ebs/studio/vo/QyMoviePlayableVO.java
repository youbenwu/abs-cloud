package com.outmao.ebs.studio.vo;

import lombok.Data;

//迁眼平台获取影视集是否可以播放
@Data
public class QyMoviePlayableVO {

    /**
     *
     * 影视集ID
     *
     */
    private Long episodeId;

    /**
     *
     * 0--等待扫码 1--需付费 2--可以播放
     *
     */
    private int status;


}
