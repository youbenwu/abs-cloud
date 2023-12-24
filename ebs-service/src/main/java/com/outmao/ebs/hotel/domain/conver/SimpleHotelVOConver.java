package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotel;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.outmao.ebs.hotel.vo.SimpleHotelVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleHotelVOConver implements BeanConver<QHotel, SimpleHotelVO> {
    @Override
    public SimpleHotelVO fromTuple(Tuple t, QHotel e) {
        SimpleHotelVO vo=new SimpleHotelVO();
        vo.setId(t.get(e.id));
        vo.setShopId(t.get(e.shopId));
        vo.setName(t.get(e.name));
        vo.setLogo(t.get(e.logo));
        vo.setImage(t.get(e.image));
        vo.setServicePhone(t.get(e.servicePhone));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotel e) {
        return new Expression[]{
                e.id,
                e.shopId,
                e.name,
                e.logo,
                e.image,
                e.servicePhone,
        };
    }
}
