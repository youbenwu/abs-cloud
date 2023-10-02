package com.outmao.ebs.mall.store.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.store.entity.QStoreSkuStockOut;
import com.outmao.ebs.mall.store.vo.StoreSkuStockOutVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class StoreSkuStockOutVOConver implements BeanConver<QStoreSkuStockOut, StoreSkuStockOutVO> {
    @Override
    public StoreSkuStockOutVO fromTuple(Tuple t, QStoreSkuStockOut e) {
        StoreSkuStockOutVO vo=new StoreSkuStockOutVO();
        vo.setId(t.get(e.id));
        vo.setStoreId(t.get(e.storeId));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setOrderId(t.get(e.orderId));
        vo.setLogisticsInfo(t.get(e.logisticsInfo));
        vo.setTo(t.get(e.to));
        vo.setStock(t.get(e.stock));
        vo.setDetails(t.get(e.details));
        vo.setRemark(t.get(e.remark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QStoreSkuStockOut e) {
        return new Expression[]{
                e.id,
                e.storeId,
                e.status,
                e.statusRemark,
                e.orderId,
                e.logisticsInfo,
                e.to,
                e.stock,
                e.details,
                e.remark,
                e.createTime,
                e.updateTime,
        };
    }
}
