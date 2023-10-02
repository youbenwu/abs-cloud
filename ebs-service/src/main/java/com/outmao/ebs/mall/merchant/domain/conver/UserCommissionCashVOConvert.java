package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QUserCommissionCash;
import com.outmao.ebs.mall.merchant.vo.UserCommissionCashVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class UserCommissionCashVOConvert implements BeanConver<QUserCommissionCash, UserCommissionCashVO> {
    @Override
    public UserCommissionCashVO fromTuple(Tuple t, QUserCommissionCash e) {
        UserCommissionCashVO vo=new UserCommissionCashVO();

        vo.setId(t.get(e.id));
        vo.setCommissionId(t.get(e.commission.id));
        vo.setUserId(t.get(e.userId));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setAmount(t.get(e.amount));
        vo.setBankName(t.get(e.bankName));
        vo.setBankAccount(t.get(e.bankAccount));
        vo.setBankCardNumber(t.get(e.bankCardNumber));
        vo.setRemark(t.get(e.remark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QUserCommissionCash e) {
        return new Expression[]{
                e.id,
                e.commission.id,
                e.userId,
                e.status,
                e.statusRemark,
                e.amount,
                e.bankName,
                e.bankAccount,
                e.bankCardNumber,
                e.remark,
                e.createTime,
                e.updateTime
        };
    }
}
