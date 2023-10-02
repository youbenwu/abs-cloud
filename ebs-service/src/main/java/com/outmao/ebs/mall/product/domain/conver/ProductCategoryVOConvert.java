package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductCategory;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductCategoryVOConvert implements BeanConver<QProductCategory, ProductCategoryVO> {

    @Override
    public ProductCategoryVO fromTuple(Tuple t, QProductCategory e) {
        ProductCategoryVO vo=new ProductCategoryVO();
        vo.setId(t.get(e.id));
        vo.setProductType(t.get(e.productType));
        vo.setTypeId(t.get(e.type.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setLevel(t.get(e.level));
        vo.setLeaf(t.get(e.leaf));
        vo.setSort(t.get(e.sort));
        vo.setImage(t.get(e.image));
        vo.setTitle(t.get(e.title));
        vo.setLetter(t.get(e.letter));
        vo.setDescription(t.get(e.description));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductCategory e) {
        return new Expression[]{
                e.id,
                e.productType,
                e.type.id,
                e.parent.id,
                e.level,
                e.leaf,
                e.sort,
                e.image,
                e.title,
                e.letter,
                e.description,
                e.createTime,
                e.updateTime
        };
    }
}
