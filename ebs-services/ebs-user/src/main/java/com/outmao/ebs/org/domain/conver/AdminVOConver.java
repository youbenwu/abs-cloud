package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QAdmin;
import com.outmao.ebs.org.vo.AdminVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AdminVOConver implements BeanConver<QAdmin, AdminVO> {


    @Override
    public AdminVO fromTuple(Tuple t, QAdmin e) {
        AdminVO vo=new AdminVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.org.id));
        vo.setUserId(t.get(e.user.id));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QAdmin e) {
        return new Expression[]{
                e.id,
                e.org.id,
                e.user.id,
                e.name,
                e.phone,
                e.updateTime,
                e.createTime,
        };
    }
}
