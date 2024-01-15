package com.outmao.ebs.user.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QUser;
import com.outmao.ebs.user.vo.QyUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class QyUserVOConver implements BeanConver<QUser, QyUserVO> {
    @Override
    public QyUserVO fromTuple(Tuple t, QUser e) {
        QyUserVO vo=new QyUserVO();
        vo.setId(t.get(e.id));
        vo.setSex(t.get(e.details.sex));
        vo.setUsername(t.get(e.username));
        vo.setNickname(t.get(e.nickname));
        vo.setAvatar(t.get(e.avatar));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUser e) {
        return new Expression[]{
                e.id,
                e.details.sex,
                e.username,
                e.nickname,
                e.avatar,
        };
    }
}
