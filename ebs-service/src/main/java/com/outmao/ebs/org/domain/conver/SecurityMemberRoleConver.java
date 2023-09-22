package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QMemberRole;
import com.outmao.ebs.security.vo.SecurityRole;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class SecurityMemberRoleConver implements BeanConver<QMemberRole, SecurityRole> {
    @Override
    public SecurityRole fromTuple(Tuple t, QMemberRole e) {
        SecurityRole role=new SecurityRole();
        role.setMemberId(t.get(e.member.id));
        role.setId(t.get(e.role.id));
        role.setValue(t.get(e.role.value));
        return role;
    }

    @Override
    public Expression<?>[] select(QMemberRole e) {
        return new Expression[]{
                e.role.id,
                e.role.value,
                e.member.id,
        };
    }
}
