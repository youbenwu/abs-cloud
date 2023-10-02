package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductAttributeGroup;
import com.outmao.ebs.mall.product.vo.ProductAttributeGroupVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

import java.util.ArrayList;

public class ProductAttributeGroupVOConver implements BeanConver<QProductAttributeGroup, ProductAttributeGroupVO> {


    @Override
    public ProductAttributeGroupVO fromTuple(Tuple t, QProductAttributeGroup e) {
        ProductAttributeGroupVO vo=new ProductAttributeGroupVO();
        vo.setId(t.get(e.id));
        vo.setKey(t.get(e.key));
        vo.setName(t.get(e.name));
        vo.setProductId(t.get(e.productId));
        vo.setAttributes(new ArrayList<>());
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductAttributeGroup e) {
        return new Expression[]{
                e.id,
                e.key,
                e.name,
                e.productId
        };
    }
}
