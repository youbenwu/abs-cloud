package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductTypeAttributeGroup;
import com.outmao.ebs.mall.product.vo.ProductTypeAttributeGroupVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductTypeAttributeGroupVOConver implements BeanConver<QProductTypeAttributeGroup, ProductTypeAttributeGroupVO> {


    @Override
    public ProductTypeAttributeGroupVO fromTuple(Tuple t, QProductTypeAttributeGroup e) {
        ProductTypeAttributeGroupVO vo=new ProductTypeAttributeGroupVO();
        vo.setId(t.get(e.id));
        vo.setTypeId(t.get(e.type.id));
        vo.setKey(t.get(e.key));
        vo.setName(t.get(e.name));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductTypeAttributeGroup e) {
        return new Expression[]{
                e.id,
                e.type.id,
                e.key,
                e.name
        };
    }


}
