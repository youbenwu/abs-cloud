package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QAdmin;
import com.outmao.ebs.security.vo.SecurityMember;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class SecurityAdminConver implements BeanConver<QAdmin, SecurityMember> {
    @Override
    public SecurityMember fromTuple(Tuple t, QAdmin e) {
        SecurityMember member=new SecurityMember();
        member.setId(t.get(e.id));
        member.setUserId(t.get(e.user.id));
        member.setOrgId(t.get(e.org.id));
        return member;
    }

    @Override
    public Expression<?>[] select(QAdmin e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.org.id,
        };
    }
}
