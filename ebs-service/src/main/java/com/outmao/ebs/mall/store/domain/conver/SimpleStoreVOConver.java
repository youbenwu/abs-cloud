package com.outmao.ebs.mall.store.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.store.entity.QStore;
import com.outmao.ebs.mall.store.vo.SimpleStoreVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleStoreVOConver implements BeanConver<QStore, SimpleStoreVO> {

    @Override
    public SimpleStoreVO fromTuple(Tuple t, QStore e) {
        SimpleStoreVO vo=new SimpleStoreVO();
        vo.setId(t.get(e.id));
        vo.setTitle(t.get(e.title));
        vo.setLogo(t.get(e.logo));
        return vo;
    }

    @Override
    public Expression<?>[] select(QStore e) {
        return new Expression[]{
                e.id,
                e.title,
                e.logo
        };
    }
}
