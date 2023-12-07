package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceLeaseOrderItem;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderItemVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelDeviceLeaseOrderItemVOConver implements BeanConver<QHotelDeviceLeaseOrderItem, HotelDeviceLeaseOrderItemVO> {
    @Override
    public HotelDeviceLeaseOrderItemVO fromTuple(Tuple t, QHotelDeviceLeaseOrderItem e) {
        HotelDeviceLeaseOrderItemVO vo=new HotelDeviceLeaseOrderItemVO();
        vo.setDeviceId(t.get(e.id));
        vo.setOrderId(t.get(e.orderId));
        vo.setId(t.get(e.id));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceLeaseOrderItem e) {
        return new Expression[]{
                e.id,
                e.orderId,
                e.deviceId,
                e.createTime
        };
    }
}
