package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelCustomerStay;
import com.outmao.ebs.hotel.vo.HotelCustomerStayVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelCustomerStayVOConver implements BeanConver<QHotelCustomerStay, HotelCustomerStayVO> {
    @Override
    public HotelCustomerStayVO fromTuple(Tuple t, QHotelCustomerStay e) {
        HotelCustomerStayVO vo=new HotelCustomerStayVO();

        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setHotelId(t.get(e.hotel.id));
        vo.setCustomerId(t.get(e.customerId));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setUserId(t.get(e.userId));
        vo.setRoomNo(t.get(e.roomNo));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setStayDays(t.get(e.stayDays));
        vo.setStartTime(t.get(e.startTime));
        vo.setEndTime(t.get(e.endTime));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelCustomerStay e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.hotel.id,
                e.customerId,
                e.status,
                e.statusRemark,
                e.userId,
                e.roomNo,
                e.name,
                e.phone,
                e.stayDays,
                e.startTime,
                e.endTime,
                e.createTime,
                e.updateTime,

        };
    }
}
