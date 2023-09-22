package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 专题文章
 *
 */
@Data
@Entity
@Table(name = "portal_TopicArticle", uniqueConstraints = { @UniqueConstraint(columnNames = { "topicId", "articleId" }) })
public class TopicArticle implements Serializable {

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


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "topicId")
    private Topic topic;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;


}
