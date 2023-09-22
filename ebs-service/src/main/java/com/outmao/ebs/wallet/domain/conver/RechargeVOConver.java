package com.outmao.ebs.wallet.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.wallet.entity.QRecharge;
import com.outmao.ebs.wallet.vo.RechargeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class RechargeVOConver implements BeanConver<QRecharge, RechargeVO> {
    @Override
    public RechargeVO fromTuple(Tuple t, QRecharge e) {
        RechargeVO vo=new RechargeVO();
        vo.setId(t.get(e.id));
        vo.setWalletId(t.get(e.wallet.id));
        vo.setRechargeNo(t.get(e.rechargeNo));
        vo.setRechargeTradeNo(t.get(e.rechargeTradeNo));
        vo.setCurrencyId(t.get(e.currencyId));
        vo.setQuantity(t.get(e.quantity));
        vo.setAmount(t.get(e.amount));
        vo.setRemark(t.get(e.remark));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setSuccessTime(t.get(e.successTime));
        vo.setFinishTime(t.get(e.finishTime));
        vo.setCloseTime(t.get(e.closeTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QRecharge e) {
        return new Expression[]{
                e.id,
                e.wallet.id,
                e.rechargeNo,
                e.rechargeTradeNo,
                e.currencyId,
                e.quantity,
                e.amount,
                e.remark,
                e.status,
                e.statusRemark,
                e.createTime,
                e.successTime,
                e.finishTime,
                e.closeTime
        };
    }
}
