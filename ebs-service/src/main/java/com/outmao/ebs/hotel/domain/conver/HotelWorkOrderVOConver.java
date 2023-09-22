package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelWorkOrder;
import com.outmao.ebs.hotel.vo.HotelWorkOrderVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelWorkOrderVOConver implements BeanConver<QHotelWorkOrder, HotelWorkOrderVO> {
    @Override
    public HotelWorkOrderVO fromTuple(Tuple t, QHotelWorkOrder e) {
        HotelWorkOrderVO vo=new HotelWorkOrderVO();

        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setHotelId(t.get(e.hotel.id));
        vo.setRoomNo(t.get(e.roomNo));
        vo.setName(t.get(e.name));
        vo.setContent(t.get(e.content));
        vo.setStartTime(t.get(e.startTime));
        vo.setEndTime(t.get(e.endTime));
        vo.setUserId(t.get(e.userId));
        vo.setUserName(t.get(e.userName));
        vo.setUserPhone(t.get(e.userPhone));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));


        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelWorkOrder e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.hotel.id,
                e.roomNo,
                e.name,
                e.content,
                e.startTime,
                e.endTime,
                e.userId,
                e.userName,
                e.userPhone,
                e.status,
                e.statusRemark,
                e.createTime,
                e.updateTime
        };
    }
}
