package com.outmao.ebs.hotel.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.hotel.dto.GetHotelListDTO;
import com.outmao.ebs.hotel.entity.QHotel;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.querydsl.core.types.dsl.MathExpressions.*;
import static com.querydsl.core.types.dsl.MathExpressions.radians;

public class HotelVOConver implements BeanConver<QHotel, HotelVO> {


    private NumberExpression<Double> distance=null;

    public HotelVOConver(){}

    public HotelVOConver( NumberExpression<Double> distance){
        this.distance=distance;
    }

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
        vo.setServicePhone(t.get(e.servicePhone));
        vo.setLogo(t.get(e.logo));
        vo.setImage(t.get(e.image));
        vo.setVideo(t.get(e.video));
        vo.setImages(t.get(e.images));
        vo.setRoomImages(t.get(e.roomImages));
        vo.setLicense(t.get(e.license));
        vo.setIdCardNo(t.get(e.idCardNo));
        vo.setIdCardFront(t.get(e.idCardFront));
        vo.setIdCardBack(t.get(e.idCardBack));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        if(distance!=null) {
            vo.setDistance(t.get(distance));
        }
        return vo;
    }

    @Override
    public Expression<?>[] select(QHotel e) {
        Expression<?>[] exps=new Expression[]{
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
                e.servicePhone,
                e.logo,
                e.image,
                e.video,
                e.images,
                e.roomImages,
                e.mark,
                e.star,
                e.business,
                e.estTime,
                e.license,
                e.idCardNo,
                e.idCardFront,
                e.idCardBack,
                e.createTime,
                e.updateTime,

        };

        if(distance!=null) {
            List<Expression<?>> list=new ArrayList(exps.length+1);
            list.addAll(Arrays.asList(exps));
            list.add(distance);
            exps= list.toArray(new Expression[]{});
        }

        return exps;
    }
}
