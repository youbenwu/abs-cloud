package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelDeviceLeaseOrderVOConver  implements BeanConver<QHotelDeviceLeaseOrder, HotelDeviceLeaseOrderVO>{
    @Override
    public HotelDeviceLeaseOrderVO fromTuple(Tuple t, QHotelDeviceLeaseOrder e) {
        HotelDeviceLeaseOrderVO vo=new HotelDeviceLeaseOrderVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.userId));
        vo.setOrderNo(t.get(e.orderNo));
        vo.setQuantity(t.get(e.quantity));
        vo.setAmount(t.get(e.amount));
        vo.setPrice(t.get(e.price));
        vo.setStartTime(t.get(e.startTime));
        vo.setEndTime(t.get(e.endTime));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceLeaseOrder e) {
        return new Expression[]{
                e.id,
                e.userId,
                e.orderNo,
                e.quantity,
                e.amount,
                e.price,
                e.startTime,
                e.endTime,
                e.createTime,
                e.updateTime,

        };
    }
}
