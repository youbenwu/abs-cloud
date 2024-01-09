package com.outmao.ebs.movie.entity;

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
     * 所属组织ID
     */
    private Long orgId;

    /**
     * 发布用户ID
     */
    private Long userId;

    /**
     * 主题ID
     */
    private Long subjectId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 类型ID
     */
    private Long categoryId;


    /**
     * 付费类型
     * 0--免费 1--会员 2--付费
     */
    private int feeType;


    /**
     * 影视名称
     */
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
