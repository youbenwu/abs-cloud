package com.outmao.ebs.wallet.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.wallet.entity.QTransfer;
import com.outmao.ebs.wallet.vo.TransferVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class TransferVOConver implements BeanConver<QTransfer, TransferVO> {
    @Override
    public TransferVO fromTuple(Tuple t, QTransfer e) {
        TransferVO vo=new TransferVO();
        vo.setId(t.get(e.id));
        vo.setTradeId(t.get(e.trade.id));
        vo.setFromId(t.get(e.from.id));
        vo.setToId(t.get(e.to.id));
        vo.setFromType(t.get(e.fromType));
        vo.setToType(t.get(e.toType));
        vo.setCurrencyId(t.get(e.currency.id));
        vo.setTransferNo(t.get(e.transferNo));
        vo.setAmount(t.get(e.amount));
        vo.setFromBalance(t.get(e.fromBalance));
        vo.setToBalance(t.get(e.toBalance));
        vo.setRemark(t.get(e.remark));
        vo.setBusiness(t.get(e.business));
        vo.setBusinessType(t.get(e.businessType));
        vo.setCreateTime(t.get(e.createTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QTransfer e) {
        return new Expression[]{
                e.id,
                e.trade.id,
                e.from.id,
                e.to.id,
                e.fromType,
                e.toType,
                e.currency.id,
                e.transferNo,
                e.amount,
                e.fromBalance,
                e.toBalance,
                e.remark,
                e.business,
                e.businessType,
                e.createTime,

        };
    }
}
