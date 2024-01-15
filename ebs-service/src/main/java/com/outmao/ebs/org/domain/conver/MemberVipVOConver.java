package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QMemberVip;
import com.outmao.ebs.org.vo.MemberVipVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MemberVipVOConver implements BeanConver<QMemberVip, MemberVipVO> {
    @Override
    public MemberVipVO fromTuple(Tuple t, QMemberVip e) {
        MemberVipVO vo=new MemberVipVO();
        vo.setId(t.get(e.id));
        vo.setMemberId(t.get(e.memberId));
        vo.setVip(t.get(e.vip));
        vo.setAmount(t.get(e.amount));
        vo.setExpireTime(t.get(e.expireTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMemberVip e) {
        return new Expression[]{
                e.id,
                e.memberId,
                e.vip,
                e.amount,
                e.expireTime,
                e.updateTime,
                e.createTime,
        };
    }
}
