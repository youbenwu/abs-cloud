package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductAttribute;
import com.outmao.ebs.mall.product.vo.ProductAttributeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductAttributeVOConver implements BeanConver<QProductAttribute, ProductAttributeVO> {


    @Override
    public ProductAttributeVO fromTuple(Tuple t, QProductAttribute e) {
        ProductAttributeVO vo=new ProductAttributeVO();
        vo.setProductId(t.get(e.productId));
        vo.setGroupId(t.get(e.groupId));
        vo.setId(t.get(e.id));
        vo.setKey(t.get(e.key));
        vo.setName(t.get(e.name));
        vo.setValue(t.get(e.value));
        vo.setSuffix(t.get(e.suffix));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductAttribute e) {
        return new Expression[]{
                e.id,
                e.productId,
                e.groupId,
                e.key,
                e.name,
                e.value,
                e.suffix
        };
    }
}
