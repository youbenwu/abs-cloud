package com.outmao.ebs.security.service.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QMember;
import com.outmao.ebs.security.vo.SecurityMember;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


public class SecurityMemberConver implements BeanConver<QMember, SecurityMember> {
    @Override
    public SecurityMember fromTuple(Tuple t, QMember e) {
        SecurityMember member=new SecurityMember();
        member.setId(t.get(e.id));
        member.setUserId(t.get(e.user.id));
        member.setOrgId(t.get(e.org.id));
        return member;
    }

    @Override
    public Expression<?>[] select(QMember e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.org.id,
                e.org.parent.id
        };
    }
}
