package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QAdvert;
import com.outmao.ebs.portal.vo.AdvertForPvLogVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AdvertForPvLogVOConver implements BeanConver<QAdvert, AdvertForPvLogVO> {
    @Override
    public AdvertForPvLogVO fromTuple(Tuple t, QAdvert e) {
        AdvertForPvLogVO vo=new AdvertForPvLogVO();
        vo.setId(t.get(e.id));
        vo.setBuyPrice(t.get(e.buy.price));
        vo.setType(t.get(e.type));

        return vo;
    }

    @Override
    public Expression<?>[] select(QAdvert e) {
        return new Expression[]{
                e.id,
                e.type,
                e.buy.price,
        };
    }
}
