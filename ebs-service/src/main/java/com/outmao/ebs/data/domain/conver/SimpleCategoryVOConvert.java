package com.outmao.ebs.data.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.data.entity.QCategory;
import com.outmao.ebs.data.vo.SimpleCategoryVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleCategoryVOConvert implements BeanConver<QCategory, SimpleCategoryVO> {


    @Override
    public SimpleCategoryVO fromTuple(Tuple t, QCategory e) {
        SimpleCategoryVO vo=new SimpleCategoryVO();
        vo.setId(t.get(e.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setTitle(t.get(e.title));
        return vo;
    }

    @Override
    public Expression<?>[] select(QCategory e) {
        return new Expression[]{
                e.id,
                e.parent.id,
                e.title,
        };
    }
}
