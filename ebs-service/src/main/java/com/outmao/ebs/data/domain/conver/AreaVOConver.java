package com.outmao.ebs.data.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.data.entity.QArea;
import com.outmao.ebs.data.vo.AreaVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AreaVOConver implements BeanConver<QArea, AreaVO> {
    @Override
    public AreaVO fromTuple(Tuple t, QArea e) {
        AreaVO vo=new AreaVO();
        vo.setId(t.get(e.id));
        vo.setCode(t.get(e.code));
        vo.setParentId(t.get(e.parent.id));
        vo.setLeaf(t.get(e.leaf));
        vo.setLevel(t.get(e.level));
        vo.setName(t.get(e.name));
        vo.setLetter(t.get(e.letter));
        vo.setAreaCode(t.get(e.areaCode));
        vo.setZipCode(t.get(e.zipCode));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QArea e) {
        return new Expression[]{
                e.id,
                e.code,
                e.parent.id,
                e.leaf,
                e.level,
                e.name,
                e.letter,
                e.areaCode,
                e.zipCode,
                e.createTime
        };
    }
}
