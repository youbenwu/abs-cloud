package com.outmao.ebs.org.domain.conver;

import com.alibaba.fastjson.JSON;
import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QOrg;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CacheOrgVOConvert implements BeanConver<QOrg, CacheOrgVO> {


    @Override
    public CacheOrgVO fromTuple(Tuple t, QOrg e) {
        CacheOrgVO vo=new CacheOrgVO();

        vo.setId(t.get(e.id));
        vo.setParentId(t.get(e.parent.id));
        String ps=t.get(e.parents);
        if(ps!=null){
            List<Long> parents= JSON.parseArray(ps,Long.class);
            vo.setParents(new HashSet<>(parents));
        }
        vo.setName(t.get(e.name));

        return vo;
    }

    @Override
    public Expression<?>[] select(QOrg e) {
        return new Expression[]{
                e.id,
                e.parent.id,
                e.parents,
                e.name
        };
    }


}
