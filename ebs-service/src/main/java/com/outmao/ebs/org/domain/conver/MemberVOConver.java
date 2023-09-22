package com.outmao.ebs.org.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.org.entity.QMember;
import com.outmao.ebs.org.vo.MemberVO;
import com.outmao.ebs.user.domain.conver.SimpleUserVOConver;
import com.outmao.ebs.user.vo.SimpleUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MemberVOConver implements BeanConver<QMember, MemberVO> {

    private SimpleUserVOConver simpleUserVOConver=new SimpleUserVOConver();

    @Override
    public MemberVO fromTuple(Tuple t, QMember q) {
        MemberVO vo=new MemberVO();
        vo.setId(t.get(q.id));
        vo.setUserId(t.get(q.user.id));
        vo.setOrgId(t.get(q.org.id));
        vo.setStatus(t.get(q.status));
        vo.setStatusRemark(t.get(q.statusRemark));
        vo.setName(t.get(q.name));
        vo.setAvatar(t.get(q.avatar));
        vo.setPhone(t.get(q.phone));
        vo.setEmail(t.get(q.email));
        vo.setUpdateTime(t.get(q.updateTime));
        vo.setCreateTime(t.get(q.createTime));
        SimpleUserVO user=simpleUserVOConver.fromTuple(t,q.user);
        vo.setUser(user);
        return vo;
    }

    @Override
    public Expression<?>[] select(QMember q) {
        return ArrayUtil.merge(new Expression<?>[][]{
                new Expression[]{
                        q.id,
                        q.user.id,
                        q.org.id,
                        q.status,
                        q.statusRemark,
                        q.name,
                        q.avatar,
                        q.phone,
                        q.email,
                        q.updateTime,
                        q.createTime,
                },
                simpleUserVOConver.select(q.user)
        });

    }


}
