package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QJob;
import com.outmao.ebs.org.vo.JobVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class JobVOConver implements BeanConver<QJob, JobVO> {

    @Override
    public JobVO fromTuple(Tuple t, QJob e) {
        JobVO vo=new JobVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.org.id));
        vo.setName(t.get(e.name));
        vo.setDescription(t.get(e.description));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QJob e) {
        return new Expression[]{
                e.id,
                e.org.id,
                e.name,
                e.description,
                e.createTime,
                e.updateTime
        };
    }
}
