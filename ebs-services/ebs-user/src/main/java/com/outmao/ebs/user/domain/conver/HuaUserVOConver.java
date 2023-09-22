package com.outmao.ebs.user.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QUser;
import com.outmao.ebs.user.vo.HuaUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class HuaUserVOConver implements BeanConver<QUser, HuaUserVO> {

    @Override
    public HuaUserVO fromTuple(Tuple t, QUser e) {
        HuaUserVO vo=new HuaUserVO();
        vo.setId(t.get(e.id));
        vo.setSex(t.get(e.details.sex));
        vo.setUsername(t.get(e.username));
        vo.setNickname(t.get(e.nickname));
        vo.setAvatar(t.get(e.avatar));
        vo.setRegisterTime(t.get(e.registerTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUser e) {
        return new Expression<?>[]{
                e.id,
                e.details.sex,
                e.username,
                e.nickname,
                e.avatar,
                e.registerTime
        };
    }

}
