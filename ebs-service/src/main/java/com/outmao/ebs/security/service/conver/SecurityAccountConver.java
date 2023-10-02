package com.outmao.ebs.security.service.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QAccount;
import com.outmao.ebs.security.vo.SecurityMember;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class SecurityAccountConver implements BeanConver<QAccount, SecurityMember> {
    @Override
    public SecurityMember fromTuple(Tuple t, QAccount e) {
        SecurityMember member=new SecurityMember();
        member.setId(t.get(e.id));
        member.setUserId(t.get(e.user.id));
        member.setOrgId(t.get(e.org.id));
        return member;
    }

    @Override
    public Expression<?>[] select(QAccount e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.org.id,
        };
    }
}
