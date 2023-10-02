package com.outmao.ebs.mall.shop.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.shop.entity.QShop;
import com.outmao.ebs.mall.shop.vo.SimpleShopVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleShopVOConver implements BeanConver<QShop, SimpleShopVO> {
    @Override
    public SimpleShopVO fromTuple(Tuple t, QShop e) {
        SimpleShopVO vo=new SimpleShopVO();
        vo.setId(t.get(e.id));
        vo.setLogo(t.get(e.logo));
        vo.setTitle(t.get(e.title));
        return vo;
    }

    @Override
    public Expression<?>[] select(QShop e) {
        return new Expression[]{
                e.id,
                e.logo,
                e.title
        };
    }
}
