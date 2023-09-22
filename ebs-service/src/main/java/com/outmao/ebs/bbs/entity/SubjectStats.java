package com.outmao.ebs.bbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "SubjectStats", description = "主题统计信息")
@Data
@Entity
@Table(name = "bbs_SubjectStats")
public class SubjectStats implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    /**
     *
     * 评分
     *
     */
    @ApiModelProperty(name = "grade", value = "评分")
    private double grade;

    /**
     * 贴子数
     */
    @ApiModelProperty(name = "posts", value = "posts")
    private int posts;

    /**
     * 分享数
     */
    @ApiModelProperty(name = "shares", value = "分享数")
    private int shares;

    /**
     * 浏览数
     */
    @ApiModelProperty(name = "browses", value = "browses")
    private int browses;

    /**
     * 收藏数
     */
    @ApiModelProperty(name = "favs", value = "收藏次数")
    private int favs;

    /**
     * 点赞次数
     */
    @ApiModelProperty(name = "likes", value = "点赞次数")
    private int likes;

    /**
     * 点灭次数
     */
    @ApiModelProperty(name = "dislikes", value = "点灭次数")
    private int dislikes;

    /**
     * 被投诉次数
     */
    @ApiModelProperty(name = "plaints", value = "投诉次数")
    private int plaints;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

}
