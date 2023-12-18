package com.outmao.ebs.message.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.message.entity.QMessageTemplateTag;
import com.outmao.ebs.message.vo.MessageTemplateTagVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MessageTemplateTagVOConver implements BeanConver<QMessageTemplateTag, MessageTemplateTagVO> {
    @Override
    public MessageTemplateTagVO fromTuple(Tuple t, QMessageTemplateTag e) {
        MessageTemplateTagVO vo=new MessageTemplateTagVO();
        vo.setId(t.get(e.id));
        vo.setName(t.get(e.name));
        vo.setTypeId(t.get(e.type.id));
        vo.setValue(t.get(e.value));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMessageTemplateTag e) {
        return new Expression[]{
                e.id,
                e.name,
                e.type.id,
                e.value,
        };
    }
}
