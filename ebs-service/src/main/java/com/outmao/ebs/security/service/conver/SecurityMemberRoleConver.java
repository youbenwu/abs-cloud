package com.outmao.ebs.security.service.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QMemberRole;
import com.outmao.ebs.security.vo.SecurityRole;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


public class SecurityMemberRoleConver implements BeanConver<QMemberRole, SecurityRole> {
    @Override
    public SecurityRole fromTuple(Tuple t, QMemberRole e) {
        SecurityRole role=new SecurityRole();
        role.setId(t.get(e.role.id));
        role.setName(t.get(e.role.name));
        role.setMemberId(t.get(e.member.id));
        return role;
    }

    @Override
    public Expression<?>[] select(QMemberRole e) {
        return new Expression[]{
                e.role.id,
                e.role.name,
                e.member.id
        };
    }
}
