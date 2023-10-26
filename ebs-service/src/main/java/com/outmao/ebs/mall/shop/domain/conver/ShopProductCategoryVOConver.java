package com.outmao.ebs.mall.shop.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.shop.entity.QShopProductCategory;
import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ShopProductCategoryVOConver implements BeanConver<QShopProductCategory, ShopProductCategoryVO> {


    @Override
    public ShopProductCategoryVO fromTuple(Tuple t, QShopProductCategory e) {
        ShopProductCategoryVO vo=new ShopProductCategoryVO();
        vo.setId(t.get(e.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setLevel(t.get(e.level));
        vo.setLeaf(t.get(e.leaf));
        vo.setSort(t.get(e.sort));
        vo.setProductType(t.get(e.productType));
        vo.setImage(t.get(e.image));
        vo.setTitle(t.get(e.title));
        vo.setDescription(t.get(e.description));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QShopProductCategory e) {
        return new Expression[]{
                e.id,
                e.parent.id,
                e.level,
                e.leaf,
                e.sort,
                e.productType,
                e.image,
                e.title,
                e.description,
                e.createTime,
                e.updateTime
        };
    }
}
