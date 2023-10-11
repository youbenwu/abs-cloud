package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotelRoomType;
import com.outmao.ebs.hotel.vo.HotelRoomTypeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelRoomTypeVOConver implements BeanConver<QHotelRoomType, HotelRoomTypeVO> {
    @Override
    public HotelRoomTypeVO fromTuple(Tuple t, QHotelRoomType e) {
        HotelRoomTypeVO vo=new HotelRoomTypeVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setHotelId(t.get(e.hotel.id));
        vo.setPrice(t.get(e.price));
        vo.setNum(t.get(e.num));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelRoomType e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.hotel.id,
                e.price,
                e.num,
                e.name,
                e.intro,
                e.createTime,
                e.updateTime,
        };
    }
}
