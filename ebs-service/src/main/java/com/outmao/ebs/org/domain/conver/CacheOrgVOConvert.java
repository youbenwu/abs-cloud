package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QOrg;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;


public class CacheOrgVOConvert implements BeanConver<QOrg, CacheOrgVO> {


    @Override
    public CacheOrgVO fromTuple(Tuple t, QOrg e) {
        CacheOrgVO vo=new CacheOrgVO();

        vo.setId(t.get(e.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setName(t.get(e.name));

        return vo;
    }

    @Override
    public Expression<?>[] select(QOrg e) {
        return new Expression[]{
                e.id,
                e.parent.id,
                e.name
        };
    }


}
