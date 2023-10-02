package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QUserCommissionRecord;
import com.outmao.ebs.mall.merchant.vo.UserCommissionRecordVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class UserCommissionRecordVOConver implements BeanConver<QUserCommissionRecord, UserCommissionRecordVO> {
    @Override
    public UserCommissionRecordVO fromTuple(Tuple t, QUserCommissionRecord e) {
        UserCommissionRecordVO vo=new UserCommissionRecordVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.userId));
        vo.setCommissionId(t.get(e.commission.id));
        vo.setOrderId(t.get(e.orderId));
        vo.setLevel(t.get(e.level));
        vo.setAmount(t.get(e.amount));
        vo.setType(t.get(e.type));
        vo.setImage(t.get(e.image));
        vo.setRemark(t.get(e.remark));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUserCommissionRecord e) {
        return new Expression[]{
                e.id,
                e.userId,
                e.commission.id,
                e.orderId,
                e.level,
                e.amount,
                e.type,
                e.image,
                e.remark,
                e.createTime
        };
    }


}
