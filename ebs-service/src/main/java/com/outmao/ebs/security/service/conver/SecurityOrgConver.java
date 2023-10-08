package com.outmao.ebs.security.service.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QOrg;
import com.outmao.ebs.security.vo.SecurityOrg;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class SecurityOrgConver implements BeanConver<QOrg, SecurityOrg> {
    @Override
    public SecurityOrg fromTuple(Tuple t, QOrg e) {
        SecurityOrg org=new SecurityOrg();
        org.setOrgId(t.get(e.id));
        org.setOrgType(t.get(e.type));
        org.setOrgName(t.get(e.name));
        org.setTargetId(t.get(e.targetId));
        return org;
    }

    @Override
    public Expression<?>[] select(QOrg e) {
        return new Expression[]{
                e.id,
                e.type,
                e.name,
                e.targetId,
        };
    }
}
