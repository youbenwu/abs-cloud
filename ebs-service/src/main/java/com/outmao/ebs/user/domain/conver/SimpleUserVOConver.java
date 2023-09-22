package com.outmao.ebs.user.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QUser;
import com.outmao.ebs.user.vo.SimpleUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleUserVOConver implements BeanConver<QUser, SimpleUserVO> {

    @Override
    public SimpleUserVO fromTuple(Tuple t, QUser e) {
        SimpleUserVO vo=new SimpleUserVO();
        vo.setId(t.get(e.id));
        vo.setArea(t.get(e.area));
        vo.setUsername(t.get(e.username));
        vo.setNickname(t.get(e.nickname));
        vo.setAvatar(t.get(e.avatar));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUser e) {
        return new Expression<?>[]{
                e.id,
                e.area,
                e.username,
                e.nickname,
                e.avatar
        };
    }

}
