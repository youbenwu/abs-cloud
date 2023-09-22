package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QTopicArticle;
import com.outmao.ebs.portal.vo.TopicArticleVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class TopicArticleVOConver implements BeanConver<QTopicArticle, TopicArticleVO> {

    @Override
    public TopicArticleVO fromTuple(Tuple t, QTopicArticle e) {
        TopicArticleVO vo=new TopicArticleVO();
        vo.setId(t.get(e.id));
        vo.setTopicId(t.get(e.topic.id));
        vo.setArticleId(t.get(e.article.id));
        return vo;
    }

    @Override
    public Expression<?>[] select(QTopicArticle e) {
        return new Expression[]{
                e.id,
                e.topic.id,
                e.article.id,
                e.createTime,
        };
    }
}
