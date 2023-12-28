package com.outmao.ebs.message.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.message.entity.QMessage;
import com.outmao.ebs.message.vo.MessageVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MessageVOConver implements BeanConver<QMessage, MessageVO> {


    @Override
    public MessageVO fromTuple(Tuple t, QMessage e) {
        MessageVO vo=new MessageVO();
        vo.setFromId(t.get(e.from.id));
        vo.setTos(t.get(e.tos));
        vo.setId(t.get(e.id));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setSendType(t.get(e.sendType));
        vo.setType(t.get(e.type));
        vo.setContent(t.get(e.content));
        vo.setImage(t.get(e.image));
        vo.setItem(t.get(e.item));
        vo.setTitle(t.get(e.title));
        vo.setUrl(t.get(e.url));
        vo.setAction(t.get(e.action));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMessage e) {
        return new Expression<?>[]{
                e.id,
                e.from.id,
                e.tos,
                e.status,
                e.statusRemark,
                e.sendType,
                e.type,
                e.content,
                e.image,
                e.item,
                e.title,
                e.url,
                e.action,
                e.createTime,
                e.updateTime,
        };
    }

}
