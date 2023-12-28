package com.outmao.ebs.message.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.message.entity.QUserMessage;
import com.outmao.ebs.message.vo.UserMessageVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class UserMessageVOConver implements BeanConver<QUserMessage, UserMessageVO> {


    @Override
    public UserMessageVO fromTuple(Tuple t, QUserMessage e) {
        UserMessageVO vo=new UserMessageVO();
        vo.setFromId(t.get(e.from.id));
        vo.setToId(t.get(e.to.id));
        vo.setId(t.get(e.id));
        vo.setSendType(t.get(e.sendType));
        vo.setTarget(t.get(e.target));
        vo.setType(t.get(e.type));
        vo.setStatus(t.get(e.status));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setContent(t.get(e.message.content));
        vo.setImage(t.get(e.message.image));
        vo.setItem(t.get(e.message.item));
        vo.setTitle(t.get(e.message.title));
        vo.setUrl(t.get(e.message.url));
        vo.setAction(t.get(e.message.action));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUserMessage e) {
        return new Expression<?>[]{
                e.id,
                e.from.id,
                e.to.id,
                e.status,
                e.sendType,
                e.target,
                e.type,
                e.createTime,
                e.updateTime,
                e.message.content,
                e.message.createTime,
                e.message.image,
                e.message.item,
                e.message.title,
                e.message.url,
                e.message.action,
        };
    }


}
