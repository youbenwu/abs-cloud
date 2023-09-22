package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QRolePermission;
import com.outmao.ebs.security.vo.SecurityRolePermission;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class SecurityRolePermissionConver implements BeanConver<QRolePermission, SecurityRolePermission> {
    @Override
    public SecurityRolePermission fromTuple(Tuple t, QRolePermission e) {
        SecurityRolePermission p=new SecurityRolePermission();
        p.setRoleId(t.get(e.role.id));
        p.setUrl(t.get(e.permission.url));
        p.setName(t.get(e.permission.name));
        return p;
    }

    @Override
    public Expression<?>[] select(QRolePermission e) {
        return new Expression[]{
                e.role.id,
                e.permission.url,
                e.permission.name,
        };
    }

}
