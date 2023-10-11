package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelCustomer;
import com.outmao.ebs.hotel.vo.HotelCustomerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelCustomerVOConver implements BeanConver<QHotelCustomer,HotelCustomerVO> {
    @Override
    public HotelCustomerVO fromTuple(Tuple t, QHotelCustomer e) {
        HotelCustomerVO vo=new HotelCustomerVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setHotelId(t.get(e.hotel.id));
        vo.setUserId(t.get(e.userId));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setIdNo(t.get(e.idNo));
        vo.setStayStatus(t.get(e.stayStatus));
        vo.setAmount(t.get(e.amount));
        vo.setStays(t.get(e.stays));
        vo.setStayDays(t.get(e.stayDays));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelCustomer e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.hotel.id,
                e.userId,
                e.name,
                e.phone,
                e.idNo,
                e.stayStatus,
                e.amount,
                e.stays,
                e.stayDays,
                e.createTime,
                e.updateTime
        };
    }
}
