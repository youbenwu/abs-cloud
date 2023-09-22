package com.outmao.ebs.bbs.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "PostStats", description = "贴子统计数据")
@Data
@Entity
@Table(name = "bbs_PostStats")
public class PostStats  implements Serializable {

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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    /**
     * 回复次数
     */
    @ApiModelProperty(name = "replys", value = "回复次数")
    private int replys;

    /**
     * 收藏数
     */
    @ApiModelProperty(name = "favs", value = "收藏数")
    private int favs;

    /**
     * 浏览次数
     */
    @ApiModelProperty(name = "browses", value = "浏览次数")
    private int browses;

    /**
     * 赞成次数
     */
    @ApiModelProperty(name = "likes", value = "赞成次数")
    private int likes;

    /**
     * 反对次数
     */
    @ApiModelProperty(name = "dislikes", value = "反对次数")
    private int dislikes;

    /**
     * 被投诉次数
     */
    @ApiModelProperty(name = "plaints", value = "被投诉次数")
    private int plaints;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

}
