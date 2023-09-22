package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.TopicArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicArticleDao extends JpaRepository<TopicArticle,Long> {

    @Query("delete from TopicArticle a where a.topic.id=?1 and a.article.id in ?2")
    public void deleteAllByTopicIdAndArticleIdIn(Long topicId, List<Long> articleIdIn);

}
