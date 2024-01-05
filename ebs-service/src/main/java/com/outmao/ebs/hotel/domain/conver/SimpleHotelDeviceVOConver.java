package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDevice;
import com.outmao.ebs.hotel.vo.SimpleHotelDeviceVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleHotelDeviceVOConver implements BeanConver<QHotelDevice, SimpleHotelDeviceVO> {
    @Override
    public SimpleHotelDeviceVO fromTuple(Tuple t, QHotelDevice e) {
        SimpleHotelDeviceVO vo=new SimpleHotelDeviceVO();
        vo.setId(t.get(e.id));
        vo.setHotelId(t.get(e.hotelId));
        vo.setRoomNo(t.get(e.roomNo));
        vo.setUserId(t.get(e.userId));
        vo.setDeviceNo(t.get(e.deviceNo));
        vo.setRenterId(t.get(e.lease.renterId));
        vo.setLeaseStatus(t.get(e.lease.status));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDevice e) {
        return new Expression[]{
                e.id,
                e.hotelId,
                e.roomNo,
                e.userId,
                e.deviceNo,
                e.lease.renterId,
                e.lease.status,
        };
    }
}
