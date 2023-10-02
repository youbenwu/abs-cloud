package com.outmao.ebs.mall.merchant.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QMerchantBroker;
import com.outmao.ebs.mall.merchant.vo.MerchantBrokerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class MerchantBrokerVOConver implements BeanConver<QMerchantBroker, MerchantBrokerVO> {

    @Override
    public MerchantBrokerVO fromTuple(Tuple t, QMerchantBroker e) {
        MerchantBrokerVO vo=new MerchantBrokerVO();

        vo.setId(t.get(e.id));
        vo.setSubjectId(t.get(e.subject.id));
        vo.setMerchantId(t.get(e.merchant.id));
        vo.setUserId(t.get(e.user.id));
        vo.setStoreId(t.get(e.storeId));
        vo.setAvatar(t.get(e.avatar));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setEmail(t.get(e.email));
        vo.setBusiness(t.get(e.business));
        vo.setFees(t.get(e.fees));
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
    public Expression<?>[] select(QMerchantBroker e) {
        return new Expression[]{
                e.id,
                e.subject.id,
                e.merchant.id,
                e.user.id,
                e.storeId,
                e.avatar,
                e.name,
                e.phone,
                e.email,
                e.business,
                e.fees,
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
