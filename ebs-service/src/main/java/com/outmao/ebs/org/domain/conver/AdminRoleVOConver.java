package com.outmao.ebs.org.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.org.entity.QAdminRole;
import com.outmao.ebs.org.vo.AdminRoleVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AdminRoleVOConver implements BeanConver<QAdminRole, AdminRoleVO> {

    private RoleVOConver roleVOConver=new RoleVOConver();


    @Override
    public AdminRoleVO fromTuple(Tuple t, QAdminRole e) {
        AdminRoleVO vo=new AdminRoleVO();
        vo.setId(t.get(e.id));
        vo.setAdminId(t.get(e.admin.id));
        vo.setRoleId(t.get(e.role.id));
        vo.setCreateTime(t.get(e.createTime));
        vo.setRole(roleVOConver.fromTuple(t,e.role));
        return vo;
    }

    @Override
    public Expression<?>[] select(QAdminRole e) {
        return ArrayUtil.merge(new Expression<?>[][]{
                new Expression<?>[]{
                        e.id,
                        e.admin.id,
                        e.role.id,
                        e.createTime,
                },
                roleVOConver.select(e.role),
        });
    }


}
