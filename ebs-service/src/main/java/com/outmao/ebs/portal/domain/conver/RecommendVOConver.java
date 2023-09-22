package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QRecommend;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class RecommendVOConver<T> implements BeanConver<QRecommend, RecommendVO<T>> {
    @Override
    public RecommendVO fromTuple(Tuple t, QRecommend e) {
        RecommendVO vo=new RecommendVO();

        vo.setId(t.get(e.id));
        vo.setSort(t.get(e.sort));
        vo.setChannelId(t.get(e.channelId));
        vo.setType(t.get(e.type));
        vo.setTitle(t.get(e.title));
        vo.setImage(t.get(e.image));
        vo.setItem(t.get(e.item));

        return vo;
    }

    @Override
    public Expression<?>[] select(QRecommend e) {
        return new Expression[]{
                e.id,
                e.sort,
                e.channelId,
                e.type,
                e.title,
                e.image,
                e.item
        };
    }
}
