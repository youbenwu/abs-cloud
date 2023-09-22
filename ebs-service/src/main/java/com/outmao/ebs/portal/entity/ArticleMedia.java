package com.outmao.ebs.portal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.data.common.data.ItemMedia;
import lombok.Data;
import javax.persistence.*;


/**
 *
 * 文章相册
 *
 */
@Data
@Entity
@Table(name = "portal_ArticleMedia")
public class ArticleMedia extends ItemMedia {


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;



}
