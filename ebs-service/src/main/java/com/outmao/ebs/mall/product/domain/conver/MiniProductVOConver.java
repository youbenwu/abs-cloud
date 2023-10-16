package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProduct;
import com.outmao.ebs.mall.product.vo.MiniProductVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


public class MiniProductVOConver implements BeanConver<QProduct, MiniProductVO> {
    @Override
    public MiniProductVO fromTuple(Tuple t, QProduct e) {
        MiniProductVO vo=new MiniProductVO();
        vo.setId(t.get(e.id));
        vo.setImage(t.get(e.image));
        vo.setTitle(t.get(e.title));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProduct e) {
        return new Expression[]{
                e.id,
                e.title,
                e.image
        };
    }
}
