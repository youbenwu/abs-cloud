package com.outmao.ebs.mall.promotion.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.promotion.entity.QGifts;
import com.outmao.ebs.mall.promotion.vo.GiftsVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class GiftsVOConver implements BeanConver<QGifts, GiftsVO> {


    @Override
    public GiftsVO fromTuple(Tuple t, QGifts e) {
        GiftsVO vo=new GiftsVO();
        vo.setId(t.get(e.id));
        vo.setShopId(t.get(e.shopId));
        vo.setTitle(t.get(e.title));
        vo.setDescription(t.get(e.description));
        vo.setProductId(t.get(e.productId));
        vo.setForEver(t.get(e.forEver));
        vo.setStartTime(t.get(e.startTime));
        vo.setEndTime(t.get(e.endTime));
        vo.setStatus(t.get(e.status));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QGifts e) {
        return new Expression[]{
                e.id,
                e.shopId,
                e.title,
                e.description,
                e.productId,
                e.forEver,
                e.startTime,
                e.endTime,
                e.status,
                e.createTime,
                e.updateTime
        };
    }
}
