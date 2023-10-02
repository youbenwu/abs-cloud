package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QMerchant;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class SimpleMerchantVOConver implements BeanConver<QMerchant, SimpleMerchantVO> {

    @Override
    public SimpleMerchantVO fromTuple(Tuple t, QMerchant e) {
        SimpleMerchantVO vo=new SimpleMerchantVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.user.id));
        vo.setShopId(t.get(e.shopId));
        vo.setType(t.get(e.type));
        vo.setName(t.get(e.name));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMerchant e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.user.id,
                e.shopId,
                e.type,
                e.name
        };
    }
}
