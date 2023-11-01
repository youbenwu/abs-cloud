package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceOwner;
import com.outmao.ebs.hotel.vo.HotelDeviceOwnerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelDeviceOwnerVOConver implements BeanConver<QHotelDeviceOwner, HotelDeviceOwnerVO> {
    @Override
    public HotelDeviceOwnerVO fromTuple(Tuple t, QHotelDeviceOwner e) {
        HotelDeviceOwnerVO vo=new HotelDeviceOwnerVO();
        vo.setId(t.get(e.id));
        vo.setCommissionId(t.get(e.commissionId));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setQuantity(t.get(e.quantity));
        vo.setAmount(t.get(e.amount));
        vo.setUserId(t.get(e.userId));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceOwner e) {
        return new Expression[]{
                e.id,
                e.commissionId,
                e.name,
                e.phone,
                e.quantity,
                e.amount,
                e.userId,
                e.createTime,
                e.updateTime,
        };
    }
}
