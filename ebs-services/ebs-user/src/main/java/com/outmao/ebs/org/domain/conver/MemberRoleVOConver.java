package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.org.entity.QMemberRole;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MemberRoleVOConver implements BeanConver<QMemberRole, MemberRoleVO> {

    private RoleVOConver roleVOConver=new RoleVOConver();

    @Override
    public MemberRoleVO fromTuple(Tuple t, QMemberRole e) {
        MemberRoleVO vo=new MemberRoleVO();
        vo.setId(t.get(e.id));
        vo.setMemberId(t.get(e.member.id));
        vo.setRoleId(t.get(e.role.id));
        vo.setCreateTime(t.get(e.createTime));
        vo.setRole(roleVOConver.fromTuple(t,e.role));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMemberRole e) {
        return ArrayUtil.merge(new Expression<?>[][]{
                new Expression<?>[]{
                        e.id,
                        e.role.id,
                        e.member.id,
                        e.createTime
                },
                roleVOConver.select(e.role)
        });
    }
}
