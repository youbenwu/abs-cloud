package com.outmao.ebs.jnet.domain.factory.impl;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.jnet.entity.factory.QProductionCategory;
import com.outmao.ebs.jnet.vo.factory.ProductionCategoryVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductionCategoryVOConver implements BeanConver<QProductionCategory, ProductionCategoryVO> {
    @Override
    public ProductionCategoryVO fromTuple(Tuple t, QProductionCategory e) {
        ProductionCategoryVO vo=new ProductionCategoryVO();
        vo.setId(t.get(e.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setImage(t.get(e.image));
        vo.setCreateTime(t.get(e.createTime));
        vo.setName(t.get(e.name));
        vo.setLeaf(t.get(e.leaf));
        vo.setLevel(t.get(e.level));
        vo.setSort(t.get(e.sort));

        return vo;
    }

    @Override
    public Expression<?>[] select(QProductionCategory e) {
        return new Expression[]{
                e.id,
                e.parent.id,
                e.image,
                e.createTime,
                e.name,
                e.leaf,
                e.level,
                e.sort,
        };
    }
}
