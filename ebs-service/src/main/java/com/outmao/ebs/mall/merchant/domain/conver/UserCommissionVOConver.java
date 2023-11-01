package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QUserCommission;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class UserCommissionVOConver implements BeanConver<QUserCommission, UserCommissionVO> {
    @Override
    public UserCommissionVO fromTuple(Tuple t, QUserCommission e) {

        UserCommissionVO vo=new UserCommissionVO();
        vo.setId(t.get(e.id));
        vo.setAmount(t.get(e.amount));
        vo.setTotalAmount(t.get(e.totalAmount));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUserCommission e) {
        return new Expression[]{
                e.id,
                e.amount,
                e.totalAmount,
                e.updateTime
        };
    }
}
