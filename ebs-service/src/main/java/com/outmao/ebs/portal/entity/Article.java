package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.bbs.common.data.BindingSubject;
import com.outmao.ebs.bbs.entity.Subject;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 文章
 *
 */
@Data
@Entity
@Table(name = "portal_Article")
public class Article implements Serializable,BindingSubject {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    @Column(nullable = false)
    private Long orgId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    /**
     *
     * 用户
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 分类
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private ArticleCategory category;

    /**
     *
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除
     *
     */
    private int status;

    /**
     *
     * 文章作者
     *
     */
    private String author;


    /**
     *
     * 文章图片视频
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @OrderBy(value = "sort ASC")
    private List<ArticleMedia> medias;


    /**
     *
     * 文章标题
     *
     */
    @Column(nullable = false)
    private String title;

    /**
     *
     * 文章内容详情
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private String content;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     */
    private Date updateTime;


    @Override
    public Item toItem() {
        return new Item(id,"Article",title);
    }


}
