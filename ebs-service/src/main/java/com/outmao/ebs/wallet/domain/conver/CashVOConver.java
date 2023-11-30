package com.outmao.ebs.wallet.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.wallet.entity.QCash;
import com.outmao.ebs.wallet.vo.CashVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class CashVOConver implements BeanConver<QCash, CashVO> {
    @Override
    public CashVO fromTuple(Tuple t, QCash e) {
        CashVO vo=new CashVO();
        vo.setId(t.get(e.id));
        vo.setWalletId(t.get(e.wallet.id));
        vo.setOrderNo(t.get(e.orderNo));
        vo.setBankAccount(t.get(e.bankAccount));
        vo.setAlipayAccount(t.get(e.alipayAccount));
        vo.setAmount(t.get(e.amount));
        vo.setTotalAmount(t.get(e.totalAmount));
        vo.setFee(t.get(e.fee));
        vo.setRemark(t.get(e.remark));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setOutStatus(t.get(e.outStatus));
        vo.setOutStatusRemark(t.get(e.outStatusRemark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setSuccessTime(t.get(e.successTime));
        vo.setFinishTime(t.get(e.finishTime));
        vo.setCloseTime(t.get(e.closeTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QCash e) {
        return new Expression[]{
                e.id,
                e.wallet.id,
                e.orderNo,
                e.bankAccount,
                e.amount,
                e.totalAmount,
                e.fee,
                e.alipayAccount,
                e.remark,
                e.status,
                e.statusRemark,
                e.outStatus,
                e.outStatusRemark,
                e.createTime,
                e.successTime,
                e.finishTime,
                e.closeTime
        };
    }
}
