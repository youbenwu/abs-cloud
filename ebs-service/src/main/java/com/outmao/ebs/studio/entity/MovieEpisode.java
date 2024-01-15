package com.outmao.ebs.studio.entity;

import com.outmao.ebs.bbs.common.data.BindingSubjectId;
import com.outmao.ebs.common.vo.Item;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 影视集对象
 */
@Data
@Entity
@Table(name = "ebs_MovieEpisode",
        uniqueConstraints = {@UniqueConstraint(columnNames = { "movieId", "_index" })})
public class MovieEpisode implements Serializable, BindingSubjectId {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 所属组织ID
     *
     */
    @Column(updatable = false,nullable = false)
    private Long orgId;

    /**
     *
     * 所属影视ID
     *
     */
    @Column(nullable = false,updatable = false)
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
    @Column(updatable = false,nullable = false)
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
    @Column(name = "_index")
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


    @Override
    public Item toItem() {
        return new Item(id,"MovieEpisode",name);
    }


}
