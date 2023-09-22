package com.outmao.ebs.user.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QIdCard;
import com.outmao.ebs.user.vo.IdCardVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class IdCardVOConver implements BeanConver<QIdCard, IdCardVO> {


    @Override
    public IdCardVO fromTuple(Tuple t, QIdCard e) {
        IdCardVO vo=new IdCardVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setName(t.get(e.name));
        vo.setIdCardNo(t.get(e.idCardNo));
        vo.setImages(t.get(e.images));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QIdCard e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.status,
                e.statusRemark,
                e.name,
                e.idCardNo,
                e.images,
                e.createTime,
                e.updateTime,
        };
    }
}
