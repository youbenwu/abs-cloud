package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QAdvert;
import com.outmao.ebs.portal.vo.AdvertVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AdvertVOConver implements BeanConver<QAdvert, AdvertVO> {


    @Override
    public AdvertVO fromTuple(Tuple t, QAdvert e) {
        AdvertVO vo=new AdvertVO();
        vo.setId(t.get(e.id));
        vo.setStatus(t.get(e.status));
        vo.setTitle(t.get(e.title));
        vo.setSubtitle(t.get(e.subtitle));
        vo.setUrl(t.get(e.url));
        vo.setVideo(t.get(e.video));
        vo.setQrCode(t.get(e.qrCode));
        vo.setImage(t.get(e.image));
        vo.setImages(t.get(e.images));
        vo.setAdvertType(t.get(e.advertType));
        vo.setChannelId(t.get(e.channelId));
        vo.setCitys(t.get(e.citys));
        vo.setType(t.get(e.type));
        vo.setStartTime(t.get(e.startTime));
        vo.setEndTime(t.get(e.endTime));
        vo.setUserId(t.get(e.userId));
        vo.setOrgId(t.get(e.orgId));
        vo.setBuy(t.get(e.buy));
        vo.setBuyDisplay(t.get(e.buyDisplay));
        return vo;
    }

    @Override
    public Expression<?>[] select(QAdvert e) {
        return new Expression[]{
                e.id,
                e.status,
                e.display,
                e.title,
                e.subtitle,
                e.url,
                e.video,
                e.qrCode,
                e.image,
                e.images,
                e.advertType,
                e.channelId,
                e.citys,
                e.type,
                e.startTime,
                e.endTime,
                e.userId,
                e.orgId,
                e.buy,
                e.buyDisplay,
        };
    }


}
