package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QAdvertChannel;
import com.outmao.ebs.portal.vo.AdvertChannelVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AdvertChannelVOConver implements BeanConver<QAdvertChannel, AdvertChannelVO> {



    @Override
    public AdvertChannelVO fromTuple(Tuple t, QAdvertChannel e) {
        AdvertChannelVO vo=new AdvertChannelVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setType(t.get(e.type));
        vo.setCode(t.get(e.code));
        vo.setTitle(t.get(e.title));
        vo.setDescription(t.get(e.description));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setProductId(t.get(e.productId));
        return vo;
    }

    @Override
    public Expression<?>[] select(QAdvertChannel e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.type,
                e.code,
                e.title,
                e.description,
                e.createTime,
                e.updateTime,
                e.productId,
        };
    }


}
