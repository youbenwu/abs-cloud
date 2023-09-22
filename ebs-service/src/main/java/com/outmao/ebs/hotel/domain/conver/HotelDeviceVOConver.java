package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDevice;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelDeviceVOConver implements BeanConver<QHotelDevice, HotelDeviceVO> {
    @Override
    public HotelDeviceVO fromTuple(Tuple t, QHotelDevice e) {
        HotelDeviceVO vo=new HotelDeviceVO();

        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setHotelId(t.get(e.hotel.id));
        vo.setRoomNo(t.get(e.roomNo));
        vo.setUserId(t.get(e.userId));
        vo.setName(t.get(e.name));
        vo.setModel(t.get(e.model));
        vo.setOs(t.get(e.os));
        vo.setAppType(t.get(e.appType));
        vo.setDeviceNo(t.get(e.deviceNo));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDevice e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.hotel.id,
                e.roomNo,
                e.userId,
                e.name,
                e.model,
                e.os,
                e.appType,
                e.deviceNo,
                e.createTime,
                e.updateTime
        };
    }
}
