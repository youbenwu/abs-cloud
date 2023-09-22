package com.outmao.ebs.org.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QDepartment;
import com.outmao.ebs.org.vo.DepartmentVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class DepartmentVOConver implements BeanConver<QDepartment, DepartmentVO> {

    @Override
    public DepartmentVO fromTuple(Tuple t, QDepartment e) {
        DepartmentVO vo=new DepartmentVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.org.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setLevel(t.get(e.level));
        vo.setSort(t.get(e.sort));
        vo.setName(t.get(e.name));
        vo.setDescription(t.get(e.description));
        vo.setMembers(t.get(e.members));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QDepartment e) {
        return new Expression[]{
                e.id,
                e.org.id,
                e.parent.id,
                e.level,
                e.leaf,
                e.sort,
                e.name,
                e.description,
                e.members,
                e.createTime,
                e.updateTime,

        };
    }
}
