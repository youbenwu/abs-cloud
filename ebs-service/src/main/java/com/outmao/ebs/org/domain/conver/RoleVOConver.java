package com.outmao.ebs.org.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QRole;
import com.outmao.ebs.org.vo.RoleVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class RoleVOConver implements BeanConver<QRole, RoleVO> {

    @Override
    public RoleVO fromTuple(Tuple t, QRole e) {
        RoleVO vo=new RoleVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.org.id));
        vo.setName(t.get(e.name));
        vo.setTitle(t.get(e.title));
        vo.setDescription(t.get(e.description));
        vo.setCreateTime(t.get(e.createTime));
        vo.setSort(t.get(e.sort));
        return vo;
    }

    @Override
    public Expression<?>[] select(QRole e) {
        return new Expression[]{
                e.id,
                e.org.id,
                e.name,
                e.title,
                e.description,
                e.createTime,
                e.sort,
        };
    }
}
