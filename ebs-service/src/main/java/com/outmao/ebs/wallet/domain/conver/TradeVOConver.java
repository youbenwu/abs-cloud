package com.outmao.ebs.wallet.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.wallet.entity.QTrade;
import com.outmao.ebs.wallet.vo.TradeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class TradeVOConver implements BeanConver<QTrade, TradeVO> {
    @Override
    public TradeVO fromTuple(Tuple t, QTrade e) {
        TradeVO vo=new TradeVO();
        vo.setId(t.get(e.id));
        vo.setFromId(t.get(e.from.id));
        vo.setToId(t.get(e.to.id));
        vo.setType(t.get(e.type));
        vo.setSubject(t.get(e.subject));
        vo.setBody(t.get(e.body));
        vo.setCurrencyId(t.get(e.currency.id));
        vo.setAmount(t.get(e.amount));
        vo.setTotalAmount(t.get(e.totalAmount));
        vo.setReceiptAmount(t.get(e.receiptAmount));
        vo.setPayerAmount(t.get(e.payerAmount));
        vo.setPayeeAmount(t.get(e.payeeAmount));
        vo.setRefundAmount(t.get(e.refundAmount));
        vo.setRefundOut(t.get(e.refundOut));
        vo.setRemark(t.get(e.remark));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setFreezeExpireTime(t.get(e.freezeExpireTime));
        vo.setFreezeStatus(t.get(e.freezeStatus));
        vo.setUnfreezeTime(t.get(e.unfreezeTime));
        vo.setTimeoutTime(t.get(e.timeoutTime));
        vo.setFinishTimeoutTime(t.get(e.finishTimeoutTime));
        vo.setCreateTime(t.get(e.createTime));
        vo.setSuccessTime(t.get(e.successTime));
        vo.setFinishTime(t.get(e.finishTime));
        vo.setCloseTime(t.get(e.closeTime));
        vo.setBusiness(t.get(e.business));
        vo.setBusinessType(t.get(e.businessType));
        vo.setTradeNo(t.get(e.tradeNo));
        vo.setPayChannel(t.get(e.payChannel));
        vo.setOutPayType(t.get(e.outPayType));
        vo.setFee(t.get(e.fee));
        return vo;
    }

    @Override
    public Expression<?>[] select(QTrade e) {
        return new Expression[]{
                e.id,
                e.from.id,
                e.to.id,
                e.type,
                e.currency.id,
                e.subject,
                e.body,
                e.amount,
                e.totalAmount,
                e.receiptAmount,
                e.payerAmount,
                e.payeeAmount,
                e.refundAmount,
                e.refundOut,
                e.remark,
                e.status,
                e.statusRemark,
                e.freezeExpireTime,
                e.freezeStatus,
                e.unfreezeTime,
                e.timeoutTime,
                e.finishTimeoutTime,
                e.createTime,
                e.successTime,
                e.finishTime,
                e.closeTime,
                e.business,
                e.businessType,
                e.tradeNo,
                e.payChannel,
                e.outPayType,
                e.fee
        };
    }
}
