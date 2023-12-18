package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.outmao.ebs.hotel.vo.MinHotelDeviceLeaseOrderVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MinHotelDeviceLeaseOrderVOConver implements BeanConver<QHotelDeviceLeaseOrder, MinHotelDeviceLeaseOrderVO>{
    @Override
    public MinHotelDeviceLeaseOrderVO fromTuple(Tuple t, QHotelDeviceLeaseOrder e) {
        MinHotelDeviceLeaseOrderVO vo=new MinHotelDeviceLeaseOrderVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.userId));
        vo.setQuantity(t.get(e.quantity));
        vo.setAmount(t.get(e.amount));
        vo.setStartTime(t.get(e.startTime));
        vo.setEndTime(t.get(e.endTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceLeaseOrder e) {
        return new Expression[]{
                e.id,
                e.userId,
                e.quantity,
                e.amount,
                e.startTime,
                e.endTime,

        };
    }
}
