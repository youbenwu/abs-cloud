package com.outmao.ebs.user.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QUserCertification;
import com.outmao.ebs.user.vo.UserCertificationVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class UserCertificationVOConver implements BeanConver<QUserCertification, UserCertificationVO> {


    @Override
    public UserCertificationVO fromTuple(Tuple t, QUserCertification e) {
        UserCertificationVO vo=new UserCertificationVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setName(t.get(e.name));
        vo.setCertificateType(t.get(e.certificateType));
        vo.setCertificateNumber(t.get(e.certificateNumber));
        vo.setCertificateFront(t.get(e.certificateFront));
        vo.setCertificateBack(t.get(e.certificateBack));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUserCertification e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.status,
                e.statusRemark,
                e.name,
                e.certificateType,
                e.certificateNumber,
                e.certificateFront,
                e.certificateBack,
                e.createTime,
                e.updateTime,


        };
    }
}
