package com.outmao.ebs.portal.vo;


import lombok.Data;

import java.util.Date;

@Data
public class TopicArticleVO {

    private Long id;

    private Long topicId;

    private Long articleId;

    private ArticleVO article;

    private Date createTime;

}
