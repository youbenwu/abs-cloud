package com.outmao.ebs.studio.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.studio.entity.QStudio;
import com.outmao.ebs.studio.vo.StudioVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class StudioVOConver implements BeanConver<QStudio, StudioVO> {

    @Override
    public StudioVO fromTuple(Tuple t, QStudio e) {
        StudioVO vo=new StudioVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.userId));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QStudio e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.userId,
                e.name,
                e.intro,
                e.updateTime,
                e.createTime,
        };
    }

}
