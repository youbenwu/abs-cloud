package com.outmao.ebs.org.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QMember;
import com.outmao.ebs.org.vo.MemberVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MemberVOConver implements BeanConver<QMember, MemberVO> {

    @Override
    public MemberVO fromTuple(Tuple t, QMember q) {
        MemberVO vo=new MemberVO();
        vo.setId(t.get(q.id));
        vo.setUserId(t.get(q.user.id));
        vo.setOrgId(t.get(q.org.id));
        vo.setStatus(t.get(q.status));
        vo.setStatusRemark(t.get(q.statusRemark));
        vo.setVip(t.get(q.vip));
        vo.setName(t.get(q.name));
        vo.setAvatar(t.get(q.avatar));
        vo.setPhone(t.get(q.phone));
        vo.setEmail(t.get(q.email));
        vo.setUpdateTime(t.get(q.updateTime));
        vo.setCreateTime(t.get(q.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMember q) {
        return new Expression[]{
                q.id,
                q.user.id,
                q.org.id,
                q.status,
                q.statusRemark,
                q.vip,
                q.name,
                q.avatar,
                q.phone,
                q.email,
                q.updateTime,
                q.createTime,
        };
    }

}
