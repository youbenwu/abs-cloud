package com.outmao.ebs.mall.promotion.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.promotion.entity.QGiftsProductSku;
import com.outmao.ebs.mall.promotion.vo.GiftsProductSkuVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class GiftsProductSkuVOConver implements BeanConver<QGiftsProductSku, GiftsProductSkuVO> {
    @Override
    public GiftsProductSkuVO fromTuple(Tuple t, QGiftsProductSku e) {
        GiftsProductSkuVO vo=new GiftsProductSkuVO();
        vo.setId(t.get(e.id));
        vo.setGiftsId(t.get(e.giftsId));
        vo.setProductId(t.get(e.productId));
        vo.setSkuId(t.get(e.skuId));
        vo.setQuantity(t.get(e.quantity));
        vo.setCreateTime(t.get(e.createTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QGiftsProductSku e) {
        return new Expression[]{
                e.id,
                e.giftsId,
                e.productId,
                e.skuId,
                e.quantity,
                e.createTime,
        };
    }
}
