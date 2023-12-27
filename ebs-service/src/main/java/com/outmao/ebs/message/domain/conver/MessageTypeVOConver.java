package com.outmao.ebs.message.domain.conver;



import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.message.entity.QMessageType;
import com.outmao.ebs.message.vo.MessageTypeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MessageTypeVOConver implements BeanConver<QMessageType, MessageTypeVO> {

    @Override
    public MessageTypeVO fromTuple(Tuple t, QMessageType q) {
        MessageTypeVO vo=new MessageTypeVO();
        vo.setId(t.get(q.id));
        vo.setStatus(t.get(q.status));
        vo.setTitle(t.get(q.title));
        vo.setName(t.get(q.name));
        vo.setMsg(t.get(q.msg));
        vo.setEmail(t.get(q.email));
        vo.setSms(t.get(q.sms));
        vo.setPush(t.get(q.push));
        vo.setMp(t.get(q.mp));
        vo.setMsgTemplateId(t.get(q.msgTemplate.id));
        vo.setEmailTemplateId(t.get(q.emailTemplate.id));
        vo.setSmsTemplateId(t.get(q.smsTemplate.id));
        vo.setPushTemplateId(t.get(q.pushTemplate.id));
        vo.setMpTemplateId(t.get(q.mpTemplate.id));
        vo.setCreateTime(t.get(q.createTime));
        vo.setUpdateTime(t.get(q.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMessageType q) {
        return new Expression[]{
                q.id,
                q.status,
                q.title,
                q.name,
                q.msg,
                q.email,
                q.sms,
                q.push,
                q.mp,
                q.msgTemplate.id,
                q.emailTemplate.id,
                q.smsTemplate.id,
                q.pushTemplate.id,
                q.mpTemplate.id,
                q.createTime,
                q.updateTime,
        };
    }

}
