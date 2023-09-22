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
        vo.setCashNo(t.get(e.cashNo));
        vo.setBankAccount(t.get(e.bankAccount));
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
    public Expression<?>[] select(QCash e) {
        return new Expression[]{
                e.id,
                e.wallet.id,
                e.cashNo,
                e.bankAccount,
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
