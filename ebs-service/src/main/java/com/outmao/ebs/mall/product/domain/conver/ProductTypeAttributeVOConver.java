package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductTypeAttribute;
import com.outmao.ebs.mall.product.vo.ProductTypeAttributeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductTypeAttributeVOConver implements BeanConver<QProductTypeAttribute, ProductTypeAttributeVO> {


    @Override
    public ProductTypeAttributeVO fromTuple(Tuple t, QProductTypeAttribute e) {
        ProductTypeAttributeVO vo=new ProductTypeAttributeVO();
        vo.setId(t.get(e.id));
        vo.setTypeId(t.get(e.type.id));
        vo.setGroupId(t.get(e.group.id));
        vo.setKey(t.get(e.key));
        vo.setName(t.get(e.name));
        vo.setValue(t.get(e.value));
        vo.setSuffix(t.get(e.suffix));
        vo.setInputType(t.get(e.inputType));
        vo.setSearchType(t.get(e.searchType));
        vo.setAssoc(t.get(e.assoc));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductTypeAttribute e) {
        return new Expression[]{
                e.id,
                e.type.id,
                e.group.id,
                e.key,
                e.name,
                e.suffix,
                e.inputType,
                e.searchType,
                e.assoc
        };
    }
}
