package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QMerchantCustomer;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class MerchantCustomerVOConver implements BeanConver<QMerchantCustomer, MerchantCustomerVO> {


    @Override
    public MerchantCustomerVO fromTuple(Tuple t, QMerchantCustomer e) {
        MerchantCustomerVO vo=new MerchantCustomerVO();

        vo.setId(t.get(e.id));
        vo.setMerchantId(t.get(e.merchant.id));
        vo.setUserId(t.get(e.user.id));
        vo.setBrokerId(t.get(e.broker.id));
        vo.setPartnerId(t.get(e.partner.id));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setRemark(t.get(e.remark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QMerchantCustomer e) {
        return new Expression[]{
                e.id,
                e.merchant.id,
                e.user.id,
                e.broker.id,
                e.partner.id,
                e.name,
                e.phone,
                e.remark,
                e.createTime,
                e.updateTime
        };
    }
}
