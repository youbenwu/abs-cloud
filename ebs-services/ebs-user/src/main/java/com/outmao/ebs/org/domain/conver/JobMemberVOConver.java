package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QJobMember;
import com.outmao.ebs.org.vo.JobMemberVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class JobMemberVOConver implements BeanConver<QJobMember, JobMemberVO> {


    @Override
    public JobMemberVO fromTuple(Tuple t, QJobMember e) {
        JobMemberVO vo=new JobMemberVO();
        vo.setId(t.get(e.id));
        vo.setJobId(t.get(e.job.id));
        vo.setMemberId(t.get(e.member.id));

        return vo;
    }

    @Override
    public Expression<?>[] select(QJobMember e) {
        return new Expression[]{
                e.id,
                e.job.id,
                e.member.id,
                e.createTime,
        };
    }


}
