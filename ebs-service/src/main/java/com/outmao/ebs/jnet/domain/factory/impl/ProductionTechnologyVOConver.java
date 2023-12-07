package com.outmao.ebs.jnet.domain.factory.impl;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.jnet.entity.factory.QProductionTechnology;
import com.outmao.ebs.jnet.vo.factory.ProductionTechnologyVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductionTechnologyVOConver implements BeanConver<QProductionTechnology, ProductionTechnologyVO> {
    @Override
    public ProductionTechnologyVO fromTuple(Tuple t, QProductionTechnology e) {
        ProductionTechnologyVO vo=new ProductionTechnologyVO();
        vo.setId(t.get(e.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setLeaf(t.get(e.leaf));
        vo.setLevel(t.get(e.level));
        vo.setName(t.get(e.name));
        vo.setSuffix(t.get(e.suffix));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductionTechnology e) {
        return new Expression[]{
                e.id,
                e.parent.id,
                e.leaf,
                e.level,
                e.name,
                e.suffix,
                e.createTime,
        };
    }
}
