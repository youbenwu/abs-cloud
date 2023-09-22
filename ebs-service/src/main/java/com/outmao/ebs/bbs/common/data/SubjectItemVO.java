package com.outmao.ebs.bbs.common.data;

import com.outmao.ebs.bbs.entity.SubjectStats;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public abstract class SubjectItemVO implements SubjectItem {

    /**
     * 数据统计
     */
    @ApiModelProperty(name = "stats", value = "数据统计")
    private SubjectStats stats;

    /**
     * 是否关注
     */
    @ApiModelProperty(name = "fav", value = "是否关注")
    private Boolean fav;

    /**
     * 是否点赞
     */
    @ApiModelProperty(name = "like", value = "是否点赞")
    private Boolean like;

    /**
     * 是否反对
     */
    @ApiModelProperty(name = "dislike", value = "是否反对")
    private Boolean dislike;



}
