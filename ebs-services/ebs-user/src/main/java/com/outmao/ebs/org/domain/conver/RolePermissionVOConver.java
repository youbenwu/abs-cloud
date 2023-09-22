package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.org.entity.QRolePermission;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class RolePermissionVOConver implements BeanConver<QRolePermission, RolePermissionVO> {

    private PermissionVOConver permissionVOConver=new PermissionVOConver();

    @Override
    public RolePermissionVO fromTuple(Tuple t, QRolePermission e) {
        RolePermissionVO vo=new RolePermissionVO();
        vo.setId(t.get(e.id));
        vo.setRoleId(t.get(e.role.id));
        vo.setCreateTime(t.get(e.createTime));
        vo.setPermissionId(t.get(e.permission.id));
        vo.setPermission(permissionVOConver.fromTuple(t,e.permission));
        return vo;
    }

    @Override
    public Expression<?>[] select(QRolePermission e) {
        return ArrayUtil.merge(new Expression<?>[][]{
                new Expression<?>[]{
                        e.id,
                        e.role.id,
                        e.createTime
                },
                permissionVOConver.select(e.permission)
        });
    }

}
