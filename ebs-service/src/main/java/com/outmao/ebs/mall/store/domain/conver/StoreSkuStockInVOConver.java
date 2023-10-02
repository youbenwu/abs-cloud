package com.outmao.ebs.mall.store.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.store.entity.QStoreSkuStockIn;
import com.outmao.ebs.mall.store.vo.StoreSkuStockInVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class StoreSkuStockInVOConver implements BeanConver<QStoreSkuStockIn, StoreSkuStockInVO> {
    @Override
    public StoreSkuStockInVO fromTuple(Tuple t, QStoreSkuStockIn e) {
        StoreSkuStockInVO vo=new StoreSkuStockInVO();

        vo.setId(t.get(e.id));
        vo.setStoreId(t.get(e.storeId));
        vo.setBatchNo(t.get(e.batchNo));
        vo.setStock(t.get(e.stock));
        vo.setDetails(t.get(e.details));
        vo.setRemark(t.get(e.remark));
        vo.setCreateTime(t.get(e.createTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QStoreSkuStockIn e) {
        return new Expression[]{
                e.id,
                e.storeId,
                e.batchNo,
                e.stock,
                e.details,
                e.remark,
                e.createTime,
        };
    }
}
