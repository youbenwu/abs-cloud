package com.outmao.ebs.user.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QUser;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ContactUserVOConver implements BeanConver<QUser, ContactUserVO> {

    @Override
    public ContactUserVO fromTuple(Tuple t, QUser e) {
        ContactUserVO vo=new ContactUserVO();
        vo.setId(t.get(e.id));
        vo.setUsername(t.get(e.username));
        vo.setNickname(t.get(e.nickname));
        vo.setAvatar(t.get(e.avatar));
        vo.setPhone(t.get(e.details.phone));
        vo.setRealName(t.get(e.details.realName));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUser e) {
        return new Expression<?>[]{
                e.id,
                e.username,
                e.nickname,
                e.avatar,
                e.details.phone,
                e.details.realName,
        };
    }

}
