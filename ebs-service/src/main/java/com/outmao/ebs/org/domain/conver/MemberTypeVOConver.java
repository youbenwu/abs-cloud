package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QMemberType;
import com.outmao.ebs.org.vo.MemberTypeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MemberTypeVOConver  implements BeanConver<QMemberType, MemberTypeVO> {
    @Override
    public MemberTypeVO fromTuple(Tuple t, QMemberType e) {
        MemberTypeVO vo=new MemberTypeVO();
        vo.setId(t.get(e.id));
        vo.setMemberId(t.get(e.memberId));
        vo.setType(t.get(e.type));
        vo.setName(t.get(e.name));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMemberType e) {
        return new Expression[]{
                e.id,
                e.memberId,
                e.type,
                e.name,
                e.createTime,
        };
    }
}
