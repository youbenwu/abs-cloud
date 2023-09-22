package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QOrg;
import com.outmao.ebs.org.vo.OrgVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class OrgVOConvert implements BeanConver<QOrg,OrgVO> {


    @Override
    public OrgVO fromTuple(Tuple t, QOrg e) {
        OrgVO vo=new OrgVO();
        vo.setId(t.get(e.id));
        vo.setTargetId(t.get(e.targetId));
        vo.setParentId(t.get(e.parent.id));
        vo.setLeaf(t.get(e.leaf));
        vo.setLevel(t.get(e.level));
        vo.setOrgNo(t.get(e.orgNo));
        vo.setType(t.get(e.type));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        vo.setContact(t.get(e.contact));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QOrg e) {
        return new Expression[]{
                e.id,
                e.targetId,
                e.user.id,
                e.parent.id,
                e.leaf,
                e.level,
                e.orgNo,
                e.type,
                e.status,
                e.statusRemark,
                e.name,
                e.intro,
                e.contact,
                e.createTime,
                e.updateTime,
        };
    }
}
