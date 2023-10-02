package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductProperty;
import com.outmao.ebs.mall.product.vo.ProductPropertyVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductPropertyVOConver implements BeanConver<QProductProperty, ProductPropertyVO> {


    @Override
    public ProductPropertyVO fromTuple(Tuple t, QProductProperty e) {
        ProductPropertyVO vo=new ProductPropertyVO();

        vo.setId(t.get(e.id));
        vo.setProductId(t.get(e.productId));
        vo.setKey(t.get(e.key));
        vo.setName(t.get(e.name));
        //vo.setType(t.get(e.type));
        vo.setValue(t.get(e.value));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductProperty e) {
        return new Expression[]{
                e.id,
                e.productId,
                e.key,
                e.name,
                e.value,
                //e.type,
        };
    }
}
