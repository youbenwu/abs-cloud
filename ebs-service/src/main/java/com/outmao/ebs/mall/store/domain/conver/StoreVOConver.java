package com.outmao.ebs.mall.store.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.store.entity.QStore;
import com.outmao.ebs.mall.store.vo.StoreVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import java.util.Date;

public class StoreVOConver implements BeanConver<QStore, StoreVO> {

    @Override
    public StoreVO fromTuple(Tuple t, QStore e) {

        StoreVO vo=new StoreVO();

        vo.setId(t.get(e.id));
        vo.setType(t.get(e.type));
        vo.setSubjectId(t.get(e.subject.id));
        vo.setMerchantId(t.get(e.merchant.id));
        vo.setTitle(t.get(e.title));
        vo.setSubtitle(t.get(e.subtitle));
        vo.setIntro(t.get(e.intro));
        vo.setLogo(t.get(e.logo));
        vo.setImage(t.get(e.image));
        vo.setContact(t.get(e.contact));
        vo.setUrl(t.get(e.url));
        vo.setQrCode(t.get(e.qrCode));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());

        return vo;
    }

    @Override
    public Expression<?>[] select(QStore e) {
        return new Expression[]{
                e.id,
                e.type,
                e.subject.id,
                e.user.id,
                e.merchant.id,
                e.title,
                e.subtitle,
                e.intro,
                e.logo,
                e.image,
                e.contact,
                e.url,
                e.qrCode,
                e.status,
                e.statusRemark,
                e.createTime,
                e.updateTime,
        };
    }
}
