package com.outmao.ebs.data.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.data.entity.QJisuArea;
import com.outmao.ebs.data.vo.JisuAreaVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class JisuAreaVOConver implements BeanConver<QJisuArea, JisuAreaVO> {

    @Override
    public JisuAreaVO fromTuple(Tuple t, QJisuArea e) {
        JisuAreaVO vo=new JisuAreaVO();
        vo.setId(t.get(e.id));
        vo.setName(t.get(e.name));
        vo.setAreacode(t.get(e.areacode));
        vo.setDepth(t.get(e.depth));
        vo.setParentid(t.get(e.parentid));
        vo.setZipcode(t.get(e.zipcode));
        return vo;
    }

    @Override
    public Expression<?>[] select(QJisuArea e) {
        return new Expression[]{
                e.id,
                e.name,
                e.areacode,
                e.depth,
                e.parentid,
                e.zipcode
        };
    }
}
