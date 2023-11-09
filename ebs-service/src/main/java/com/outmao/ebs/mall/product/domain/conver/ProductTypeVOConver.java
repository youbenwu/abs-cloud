package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductType;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductTypeVOConver implements BeanConver<QProductType, ProductTypeVO> {

    @Override
    public ProductTypeVO fromTuple(Tuple t, QProductType e) {
        ProductTypeVO vo=new ProductTypeVO();
        vo.setId(t.get(e.id));
        vo.setType(t.get(e.type));
        vo.setName(t.get(e.name));
        vo.setDescription(t.get(e.description));
        vo.setSort(t.get(e.sort));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductType e) {
        return new Expression[]{
                e.id,
                e.type,
                e.name,
                e.description,
                e.sort,
                e.createTime,
                e.updateTime,
        };
    }
}
