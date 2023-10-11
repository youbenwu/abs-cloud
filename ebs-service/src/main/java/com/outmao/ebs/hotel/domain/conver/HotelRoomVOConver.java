package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.hotel.entity.QHotelRoom;
import com.outmao.ebs.hotel.vo.HotelRoomVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelRoomVOConver implements BeanConver<QHotelRoom, HotelRoomVO> {

    private HotelRoomTypeVOConver hotelRoomTypeVOConver=new HotelRoomTypeVOConver();

    @Override
    public HotelRoomVO fromTuple(Tuple t, QHotelRoom e) {
        HotelRoomVO vo=new HotelRoomVO();
        vo.setId(t.get(e.id));
        vo.setHotelId(t.get(e.hotel.id));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setTypeId(t.get(e.type.id));
        vo.setType(hotelRoomTypeVOConver.fromTuple(t,e.type));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        vo.setImage(t.get(e.image));
        vo.setRoomNo(t.get(e.roomNo));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QHotelRoom e) {
        return ArrayUtil.merge(
                new Expression[]{
                        e.id,
                        e.hotel.id,
                        e.status,
                        e.statusRemark,
                        e.type,
                        e.name,
                        e.intro,
                        e.image,
                        e.roomNo,
                        e.createTime,
                        e.updateTime
                },
                hotelRoomTypeVOConver.select(e.type)
        );
    }


}
