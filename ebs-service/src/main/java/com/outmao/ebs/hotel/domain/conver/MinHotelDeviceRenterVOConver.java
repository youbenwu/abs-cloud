package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceRenter;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import com.outmao.ebs.hotel.vo.MinHotelDeviceRenterVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;


public class MinHotelDeviceRenterVOConver implements BeanConver<QHotelDeviceRenter, MinHotelDeviceRenterVO> {
    @Override
    public MinHotelDeviceRenterVO fromTuple(Tuple t, QHotelDeviceRenter e) {
        MinHotelDeviceRenterVO vo=new MinHotelDeviceRenterVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.userId));
        vo.setAmount(t.get(e.amount));
        vo.setQuantity(t.get(e.quantity));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceRenter e) {
        return new Expression[]{
                e.id,
                e.userId,
                e.amount,
                e.quantity,
        };
    }
}
