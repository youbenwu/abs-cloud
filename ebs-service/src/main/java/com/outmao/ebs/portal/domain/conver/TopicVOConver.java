package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QTopic;
import com.outmao.ebs.portal.vo.TopicVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class TopicVOConver implements BeanConver<QTopic, TopicVO> {

    @Override
    public TopicVO fromTuple(Tuple t, QTopic e) {
        TopicVO vo=new TopicVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.userId));
        vo.setTitle(t.get(e.title));
        vo.setContent(t.get(e.content));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QTopic e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.userId,
                e.title,
                e.content,
                e.createTime,
                e.updateTime
        };
    }
}
