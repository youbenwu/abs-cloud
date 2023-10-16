package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductSku;
import com.outmao.ebs.mall.product.vo.MiniProductSkuVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;



public class MiniProductSkuVOConver implements BeanConver<QProductSku, MiniProductSkuVO> {



    @Override
    public MiniProductSkuVO fromTuple(Tuple t, QProductSku e) {
        MiniProductSkuVO vo=new MiniProductSkuVO();
        vo.setProductId(t.get(e.product.id));
        vo.setProductTitle(t.get(e.product.title));
        vo.setProductImage(t.get(e.product.image));
        vo.setSkuId(t.get(e.id));
        vo.setSkuName(t.get(e.name));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductSku e) {
        return new Expression[]{
                e.id,
                e.name,
                e.product.id,
                e.product.title,
                e.product.image
        };
    }



}
