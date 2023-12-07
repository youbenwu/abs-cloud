package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceRenter;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;


public class HotelDeviceRenterVOConver implements BeanConver<QHotelDeviceRenter, HotelDeviceRenterVO> {
    @Override
    public HotelDeviceRenterVO fromTuple(Tuple t, QHotelDeviceRenter e) {
        HotelDeviceRenterVO vo=new HotelDeviceRenterVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.userId));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setAmount(t.get(e.amount));
        vo.setQuantity(t.get(e.quantity));
        vo.setCommissionId(t.get(e.commissionId));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceRenter e) {
        return new Expression[]{
                e.id,
                e.userId,
                e.name,
                e.phone,
                e.amount,
                e.quantity,
                e.commissionId,
                e.updateTime,
                e.createTime,
        };
    }
}
