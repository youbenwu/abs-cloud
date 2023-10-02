package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductTypeProperty;
import com.outmao.ebs.mall.product.vo.ProductTypePropertyVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductTypePropertyVOConver implements BeanConver<QProductTypeProperty, ProductTypePropertyVO> {


    @Override
    public ProductTypePropertyVO fromTuple(Tuple t, QProductTypeProperty e) {
        ProductTypePropertyVO vo=new ProductTypePropertyVO();
        vo.setId(t.get(e.id));
        vo.setTypeId(t.get(e.type.id));
        vo.setKey(t.get(e.key));
        vo.setName(t.get(e.name));
        vo.setValue(t.get(e.value));
        vo.setPropertyType(t.get(e.propertyType));
        vo.setInputType(t.get(e.inputType));
        vo.setSearchType(t.get(e.searchType));
        vo.setAssoc(t.get(e.assoc));
        vo.setAdd(t.get(e.add));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductTypeProperty e) {
        return new Expression[]{
                e.id,
                e.type.id,
                e.key,
                e.name,
                e.value,
                e.propertyType,
                e.inputType,
                e.searchType,
                e.assoc,
                e.add,

        };
    }
}
