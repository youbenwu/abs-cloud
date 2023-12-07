package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceLease;
import com.outmao.ebs.hotel.entity.QHotelDeviceLeaseRecord;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseRecordVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelDeviceLeaseRecordVOConver implements BeanConver<QHotelDeviceLeaseRecord, HotelDeviceLeaseRecordVO> {


    @Override
    public HotelDeviceLeaseRecordVO fromTuple(Tuple t, QHotelDeviceLeaseRecord e) {
        HotelDeviceLeaseRecordVO vo=new HotelDeviceLeaseRecordVO();
        vo.setId(t.get(e.id));
        vo.setDeviceId(t.get(e.deviceId));
        vo.setUserId(t.get(e.userId));
        vo.setPartnerId(t.get(e.partnerId));
        vo.setStatus(t.get(e.status));
        vo.setAmount(t.get(e.amount));
        vo.setStartTime(t.get(e.startTime));
        vo.setEndTime(t.get(e.endTime));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceLeaseRecord e) {
        return new Expression[]{
                e.id,
                e.deviceId,
                e.userId,
                e.partnerId,
                e.status,
                e.amount,
                e.startTime,
                e.endTime,
                e.createTime,
                e.updateTime,
        };
    }
}
