package com.outmao.ebs.message.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.message.entity.QMessageTemplate;
import com.outmao.ebs.message.vo.MessageTemplateVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MessageTemplateVOConver implements BeanConver<QMessageTemplate, MessageTemplateVO> {


    @Override
    public MessageTemplateVO fromTuple(Tuple t, QMessageTemplate e) {
        MessageTemplateVO vo=new MessageTemplateVO();
        vo.setId(t.get(e.id));
        vo.setTypeId(t.get(e.type.id));
        vo.setSendType(t.get(e.sendType));
        vo.setTitle(t.get(e.title));
        vo.setName(t.get(e.name));
        vo.setContent(t.get(e.content));
        vo.setUrl(t.get(e.url));
        vo.setAction(t.get(e.action));
        vo.setTemplateId(t.get(e.templateId));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMessageTemplate e) {
        return new Expression[]{
                e.id,
                e.type.id,
                e.sendType,
                e.title,
                e.name,
                e.content,
                e.url,
                e.action,
                e.templateId,
                e.createTime,
                e.updateTime,

        };
    }


}
