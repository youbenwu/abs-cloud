package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QMerchantCustomer;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantCustomerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class SimpleMerchantCustomerVOConver implements BeanConver<QMerchantCustomer, SimpleMerchantCustomerVO> {


    @Override
    public SimpleMerchantCustomerVO fromTuple(Tuple t, QMerchantCustomer e) {
        SimpleMerchantCustomerVO vo=new SimpleMerchantCustomerVO();

        vo.setId(t.get(e.id));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));

        return vo;
    }

    @Override
    public Expression<?>[] select(QMerchantCustomer e) {
        return new Expression[]{
                e.id,
                e.name,
                e.phone
        };
    }
}
