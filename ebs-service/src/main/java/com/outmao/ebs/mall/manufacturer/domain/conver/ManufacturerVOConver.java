package com.outmao.ebs.mall.manufacturer.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.manufacturer.entity.QManufacturer;
import com.outmao.ebs.mall.manufacturer.vo.ManufacturerVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ManufacturerVOConver implements BeanConver<QManufacturer, ManufacturerVO> {


    @Override
    public ManufacturerVO fromTuple(Tuple t, QManufacturer e) {
        ManufacturerVO vo=new ManufacturerVO();

        vo.setId(t.get(e.id));
        vo.setName(t.get(e.name));
        vo.setCreateTime(t.get(e.createTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QManufacturer e) {
        return new Expression[]{
                e.id,
                e.name,
                e.createTime
        };
    }


}
