package com.outmao.ebs.security.service.conver;

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
        p.setUrl(t.get(e.permission.url));
        p.setName(t.get(e.permission.name));
        p.setRoleId(t.get(e.role.id));
        return p;
    }

    @Override
    public Expression<?>[] select(QRolePermission e) {
        return new Expression[]{
                e.permission.url,
                e.permission.name,
                e.role.id
        };
    }

}
