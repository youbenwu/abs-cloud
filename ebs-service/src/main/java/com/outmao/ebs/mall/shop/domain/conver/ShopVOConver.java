package com.outmao.ebs.mall.shop.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.shop.entity.QShop;
import com.outmao.ebs.mall.shop.vo.ShopVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ShopVOConver implements BeanConver<QShop, ShopVO> {


    @Override
    public ShopVO fromTuple(Tuple t, QShop e) {
        ShopVO vo=new ShopVO();
        vo.setId(t.get(e.id));
        vo.setSubjectId(t.get(e.subjectId));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.userId));
        vo.setMerchantId(t.get(e.merchantId));
        vo.setTitle(t.get(e.title));
        vo.setSubtitle(t.get(e.subtitle));
        vo.setIntro(t.get(e.intro));
        vo.setImage(t.get(e.image));
        vo.setLogo(t.get(e.logo));
        vo.setQrCode(t.get(e.qrCode));
        vo.setUrl(t.get(e.url));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QShop e) {
        return new Expression[]{
                e.id,
                e.subjectId,
                e.orgId,
                e.userId,
                e.merchantId,
                e.title,
                e.subtitle,
                e.intro,
                e.image,
                e.logo,
                e.qrCode,
                e.url,
                e.status,
                e.statusRemark,
                e.createTime,
                e.updateTime
        };
    }
}
