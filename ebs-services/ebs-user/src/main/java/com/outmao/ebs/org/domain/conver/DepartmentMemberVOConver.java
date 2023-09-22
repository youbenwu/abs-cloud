package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QDepartmentMember;
import com.outmao.ebs.org.vo.DepartmentMemberVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class DepartmentMemberVOConver implements BeanConver<QDepartmentMember, DepartmentMemberVO> {


    @Override
    public DepartmentMemberVO fromTuple(Tuple t, QDepartmentMember e) {
        DepartmentMemberVO vo=new DepartmentMemberVO();
        vo.setId(t.get(e.id));
        vo.setDepartmentId(t.get(e.department.id));
        vo.setMemberId(t.get(e.member.id));
        return vo;
    }

    @Override
    public Expression<?>[] select(QDepartmentMember e) {
        return new Expression[]{
                e.id,
                e.department.id,
                e.member.id,
        };
    }
}
