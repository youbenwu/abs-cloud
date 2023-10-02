package com.outmao.ebs.mall.store.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.store.entity.QStoreProduct;
import com.outmao.ebs.mall.store.vo.StoreProductVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class StoreProductVOConver implements BeanConver<QStoreProduct, StoreProductVO> {

    @Override
    public StoreProductVO fromTuple(Tuple t, QStoreProduct e) {
        StoreProductVO vo=new StoreProductVO();
        vo.setId(t.get(e.id));
        vo.setStoreId(t.get(e.store.id));
        vo.setCategoryId(t.get(e.category.id));
        vo.setProductId(t.get(e.product.id));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QStoreProduct e) {
        return new Expression[]{
                e.id,
                e.store.id,
                e.category.id,
                e.product.id,
                e.createTime,
        };
    }


}
