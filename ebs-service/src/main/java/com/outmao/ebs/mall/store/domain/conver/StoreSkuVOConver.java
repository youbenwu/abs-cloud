package com.outmao.ebs.mall.store.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.store.entity.QStoreSku;
import com.outmao.ebs.mall.store.vo.StoreSkuVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class StoreSkuVOConver implements BeanConver<QStoreSku, StoreSkuVO>{



    @Override
    public StoreSkuVO fromTuple(Tuple t, QStoreSku e) {
        StoreSkuVO vo=new StoreSkuVO();

        vo.setId(t.get(e.id));
        vo.setStoreId(t.get(e.store.id));
        vo.setProductId(t.get(e.product.id));
        vo.setSkuId(t.get(e.skuId));
        vo.setSkuName(t.get(e.skuName));
        vo.setSkuNo(t.get(e.skuNo));
        vo.setStock(t.get(e.stock));
        vo.setAlarmStock(t.get(e.availableStock));
        vo.setAlarmStock(t.get(e.alarmStock));
        vo.setStatus(t.get(e.status));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QStoreSku e) {
        return new Expression[]{
                e.id,
                e.store.id,
                e.product.id,
                e.skuId,
                e.skuName,
                e.skuNo,
                e.stock,
                e.availableStock,
                e.alarmStock,
                e.status,
                e.createTime,
                e.updateTime,
        };
    }
}
