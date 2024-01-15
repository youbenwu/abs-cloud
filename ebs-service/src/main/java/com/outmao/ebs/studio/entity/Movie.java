package com.outmao.ebs.studio.entity;

import com.outmao.ebs.bbs.common.data.BindingSubjectId;
import com.outmao.ebs.common.vo.Item;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 影视对象
 */
@Data
@Entity
@Table(name = "ebs_Movie")
public class Movie implements Serializable, BindingSubjectId {

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
     * 发布用户ID
     */
    @Column(updatable = false,nullable = false)
    private Long userId;

    /**
     * 主题ID
     */
    private Long subjectId;

    /**
     * 类型ID
     */
    @Column(nullable = false)
    private Long categoryId;

    /**
     * 付费类型
     * 0--免费 1--会员 2--付费
     */
    private int feeType;


    /**
     * 商品ID
     */
    private Long productId;

    /**
     *
     * 价格
     *
     */
    private double price;

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    /**
     * 影视名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 影视简介
     */
    private String intro;

    /**
     * 影视封面
     */
    private String cover;

    /**
     * 上映时间
     */
    private Date releaseTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    public Item toItem() {
        return new Item(id,"Movie",name);
    }



}
