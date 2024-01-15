package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QOrgType;
import com.outmao.ebs.org.vo.OrgTypeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class OrgTypeVOConver implements BeanConver<QOrgType, OrgTypeVO> {


    @Override
    public OrgTypeVO fromTuple(Tuple t, QOrgType e) {
        OrgTypeVO vo=new OrgTypeVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setType(t.get(e.type));
        vo.setTargetId(t.get(e.targetId));
        vo.setName(t.get(e.name));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QOrgType e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.type,
                e.targetId,
                e.name,
                e.createTime,
        };
    }
}
