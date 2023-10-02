package com.outmao.ebs.mall.takeLook.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.takeLook.entity.QTakeLook;
import com.outmao.ebs.mall.takeLook.vo.TakeLookVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class TakeLookVOConver implements BeanConver<QTakeLook, TakeLookVO> {
    @Override
    public TakeLookVO fromTuple(Tuple t, QTakeLook e) {
        TakeLookVO vo=new TakeLookVO();
        vo.setId(t.get(e.id));
        vo.setMerchantId(t.get(e.merchantId));
        vo.setStoreId(t.get(e.storeId));
        vo.setBrokerId(t.get(e.brokerId));
        vo.setCustomerId(t.get(e.customerId));
        vo.setWaiterId(t.get(e.waiterId));
        vo.setUserId(t.get(e.userId));
        vo.setFeedback(t.get(e.feedback));
        vo.setProductType(t.get(e.productType));
        vo.setStatus(t.get(e.status));
        vo.setAppointmentTime(t.get(e.appointmentTime));
        vo.setLookTime(t.get(e.lookTime));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QTakeLook e) {
        return new Expression[]{
                e.id,
                e.merchantId,
                e.storeId,
                e.brokerId,
                e.customerId,
                e.waiterId,
                e.userId,
                e.feedback,
                e.productType,
                e.status,
                e.lookTime,
                e.appointmentTime,
                e.appointmentTime,
                e.createTime,
                e.updateTime
        };
    }
}
