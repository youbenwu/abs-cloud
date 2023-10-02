package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QMerchantPartner;
import com.outmao.ebs.mall.merchant.vo.MerchantPartnerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class MerchantPartnerVOConver implements BeanConver<QMerchantPartner, MerchantPartnerVO> {
    @Override
    public MerchantPartnerVO fromTuple(Tuple t, QMerchantPartner e) {
        MerchantPartnerVO vo=new MerchantPartnerVO();
        vo.setId(t.get(e.id));
        vo.setMerchantId(t.get(e.merchant.id));
        vo.setUserId(t.get(e.user.id));
        vo.setBrokerId(t.get(e.broker.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setLevel(t.get(e.level));
        vo.setLeaf(t.get(e.leaf));
        vo.setAvatar(t.get(e.avatar));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setEmail(t.get(e.email));
        vo.setUrl(t.get(e.url));
        vo.setQrCode(t.get(e.qrCode));
        vo.setCode(t.get(e.code));
        vo.setPyramidQrCode(t.get(e.pyramidQrCode));
        vo.setBrokerQrCode(t.get(e.brokerQrCode));
        vo.setStatus(t.get(e.status));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMerchantPartner e) {
        return new Expression[]{
                e.id,
                e.merchant.id,
                e.user.id,
                e.broker.id,
                e.parent.id,
                e.level,
                e.leaf,
                e.avatar,
                e.name,
                e.phone,
                e.email,
                e.url,
                e.qrCode,
                e.code,
                e.brokerQrCode,
                e.pyramidQrCode,
                e.status,
                e.createTime,
                e.updateTime
        };
    }
}
