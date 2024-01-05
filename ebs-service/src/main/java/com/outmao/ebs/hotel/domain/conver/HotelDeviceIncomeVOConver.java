package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelDeviceIncome;
import com.outmao.ebs.hotel.vo.HotelDeviceIncomeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelDeviceIncomeVOConver implements BeanConver<QHotelDeviceIncome, HotelDeviceIncomeVO> {


    @Override
    public HotelDeviceIncomeVO fromTuple(Tuple t, QHotelDeviceIncome e) {
        HotelDeviceIncomeVO vo=new HotelDeviceIncomeVO();
        vo.setId(t.get(e.id));
        vo.setDeviceId(t.get(e.deviceId));
        vo.setCreateTime(t.get(e.createTime));
        vo.setFee(t.get(e.fee));
        vo.setTotalAmount(t.get(e.totalAmount));
        vo.setTotalFee(t.get(e.totalFee));
        vo.setRenterId(t.get(e.renterId));
        vo.setRenterFee(t.get(e.renterFee));
        vo.setType(t.get(e.type));
        vo.setOrderNo(t.get(e.orderNo));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelDeviceIncome e) {
        return new Expression[]{
                e.id,
                e.deviceId,
                e.createTime,
                e.fee,
                e.totalAmount,
                e.totalFee,
                e.renterFee,
                e.renterId,
                e.type,
                e.orderNo,
        };
    }


}
