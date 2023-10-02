package com.outmao.ebs.mall.merchant.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QMerchantBroker;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class SimpleMerchantBrokerVOConver implements BeanConver<QMerchantBroker, SimpleMerchantBrokerVO> {

    @Override
    public SimpleMerchantBrokerVO fromTuple(Tuple t, QMerchantBroker e) {
        SimpleMerchantBrokerVO vo=new SimpleMerchantBrokerVO();

        vo.setId(t.get(e.id));
        vo.setMerchantId(t.get(e.merchant.id));
        vo.setStoreId(t.get(e.storeId));
        vo.setUserId(t.get(e.user.id));
        vo.setAvatar(t.get(e.avatar));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setMerchantName(t.get(e.merchant.name));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMerchantBroker e) {
        return new Expression[]{
                e.id,
                e.merchant.id,
                e.user.id,
                e.storeId,
                e.avatar,
                e.name,
                e.phone,
                e.merchant.name
        };
    }
}
