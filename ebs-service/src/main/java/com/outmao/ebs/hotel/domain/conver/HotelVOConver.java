package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.entity.QHotel;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HotelVOConver implements BeanConver<QHotel, HotelVO> {
    @Override
    public HotelVO fromTuple(Tuple t, QHotel e) {
        HotelVO vo=new HotelVO();
        vo.setId(t.get(e.id));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.userId));
        vo.setMerchantId(t.get(e.merchantId));
        vo.setShopId(t.get(e.shopId));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        vo.setContact(t.get(e.contact));
        vo.setLogo(t.get(e.logo));
        vo.setImage(t.get(e.image));
        vo.setLicense(t.get(e.license));
        vo.setIdCardNo(t.get(e.idCardNo));
        vo.setIdCardFront(t.get(e.idCardFront));
        vo.setIdCardBack(t.get(e.idCardBack));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotel e) {
        return new Expression[]{
                e.id,
                e.status,
                e.statusRemark,
                e.orgId,
                e.userId,
                e.merchantId,
                e.shopId,
                e.name,
                e.intro,
                e.contact,
                e.logo,
                e.image,
                e.license,
                e.idCardNo,
                e.idCardFront,
                e.idCardBack,
                e.createTime,
                e.updateTime,

        };
    }
}
