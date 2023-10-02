package com.outmao.ebs.mall.manufacturer.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.manufacturer.entity.QCounselor;
import com.outmao.ebs.mall.manufacturer.vo.CounselorVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class CounselorVOConver  implements BeanConver<QCounselor, CounselorVO> {
    @Override
    public CounselorVO fromTuple(Tuple t, QCounselor e) {
        CounselorVO vo=new CounselorVO();

        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setManufacturerId(t.get(e.manufacturer.id));
        vo.setManufacturerName(t.get(e.manufacturer.name));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setCreateTime(t.get(e.createTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QCounselor e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.manufacturer.id,
                e.manufacturer.name,
                e.name,
                e.phone,
                e.createTime
        };
    }
}
