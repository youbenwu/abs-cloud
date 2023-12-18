package com.outmao.ebs.message.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.message.common.constant.SendType;
import com.outmao.ebs.message.dao.MessageTemplateDao;
import com.outmao.ebs.message.dao.MessageTemplateTagDao;
import com.outmao.ebs.message.dao.MessageTypeDao;
import com.outmao.ebs.message.domain.MessageTypeDomain;
import com.outmao.ebs.message.domain.conver.MessageTemplateTagVOConver;
import com.outmao.ebs.message.domain.conver.MessageTemplateVOConver;
import com.outmao.ebs.message.domain.conver.MessageTypeVOConver;
import com.outmao.ebs.message.dto.GetMessageTemplateListDTO;
import com.outmao.ebs.message.dto.MessageTemplateDTO;
import com.outmao.ebs.message.dto.MessageTemplateTagDTO;
import com.outmao.ebs.message.dto.MessageTypeDTO;
import com.outmao.ebs.message.entity.*;
import com.outmao.ebs.message.vo.MessageTemplateTagVO;
import com.outmao.ebs.message.vo.MessageTemplateVO;
import com.outmao.ebs.message.vo.MessageTypeVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MessageTypeDomainImpl extends BaseDomain implements MessageTypeDomain {


    @Autowired
    private MessageTypeDao messageTypeDao;

    @Autowired
    private MessageTemplateTagDao messageCategoryTagDao;

    @Autowired
    private MessageTemplateDao messageTemplateDao;


    private MessageTypeVOConver messageTypeVOConver=new MessageTypeVOConver();
    private MessageTemplateVOConver messageTemplateVOConver=new MessageTemplateVOConver();
    private MessageTemplateTagVOConver messageTemplateTagVOConver=new MessageTemplateTagVOConver();



    private List<MessageTemplateTag> getMessageTemplateTagList(Long categoryId) {
        return messageCategoryTagDao.findAllByTypeId(categoryId);
    }

    private void saveMessageCategoryTagList(MessageType category, List<MessageTemplateTagDTO> list) {
        List<MessageTemplateTag> tags=new ArrayList<>(list.size());
        list.forEach(t->{
            MessageTemplateTag tag=t.getId()==null?messageCategoryTagDao.findByTypeIdAndValue(category.getId(),t.getValue())
                    :messageCategoryTagDao.getOne(t.getId());
            if(tag==null){
                tag=new MessageTemplateTag();
                tag.setType(category);
            }
            BeanUtils.copyProperties(t,tag);
            tags.add(tag);
        });

        messageCategoryTagDao.saveAll(tags);

        List<Long> inIn=tags.stream().map(t->t.getId()).collect(Collectors.toList());

        messageCategoryTagDao.deleteAllByTypeIdAndIdNotIn(category.getId(),inIn);

    }

    @CacheEvict(value = "cache_message_type", key = "#request.name")
    @Transactional
    @Override
    public MessageType saveMessageType(MessageTypeDTO request) {

        MessageType category = request.getId()!=null? messageTypeDao.getOne(request.getId())
                : messageTypeDao.findByName(request.getName());
        if(category==null){
            category = new MessageType();
            category.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request, category);

        category.setMsgTemplate(request.getMsgTemplateId()==null?null:messageTemplateDao.getOne(request.getMsgTemplateId()));
        category.setEmailTemplate(request.getEmailTemplateId()==null?null:messageTemplateDao.getOne(request.getEmailTemplateId()));
        category.setSmsTemplate(request.getSmsTemplateId()==null?null:messageTemplateDao.getOne(request.getSmsTemplateId()));
        category.setPushTemplate(request.getPushTemplateId()==null?null:messageTemplateDao.getOne(request.getPushTemplateId()));
        category.setMpTemplate(request.getMpTemplateId()==null?null:messageTemplateDao.getOne(request.getMpTemplateId()));

        category.setUpdateTime(new Date());

        messageTypeDao.save(category);

        if(request.getTags()!=null){
            saveMessageCategoryTagList(category, request.getTags());
        }

        return category;
    }


    @CacheEvict(value = "cache_message_type", allEntries = true)
    @Transactional
    @Override
    public void deleteMessageTypeById(Long id) {
        //删除关联标签
        messageCategoryTagDao.deleteAllByTypeId(id);
        //删除关联模板
        messageTypeDao.setTemplateNullById(id);
        //删除模板
        messageTemplateDao.deleteAllByTypeId(id);
        //删除
        messageTypeDao.deleteById(id);
    }

    @Override
    public MessageType getMessageTypeById(Long id) {
        return messageTypeDao.findById(id).orElse(null);
    }



    @Override
    public MessageType getMessageTypeByName(String name) {
        return messageTypeDao.findByName(name);
    }


    @Override
    public List<MessageType> getMessageTypeList() {
        return messageTypeDao.findAll();
    }




    @Cacheable(value = "cache_message_type", key = "#name",unless = "#result == null")
    @Override
    public MessageTypeVO getMessageTypeVOByName(String name) {
        QMessageType e=QMessageType.messageType;
        MessageTypeVO vo= queryOne(e,e.name.eq(name),messageTypeVOConver);
        if(vo!=null) {
            vo.setTags(getMessageTemplateTagVOList(vo.getId()));
            List list = new ArrayList(1);
            list.add(vo);
            setTemplateData(list);
        }
        return vo;
    }

    @Override
    public MessageTypeVO getMessageTypeVOById(Long id) {
        QMessageType e=QMessageType.messageType;
        MessageTypeVO vo= queryOne(e,e.id.eq(id),new MessageTypeVOConver());
        if(vo!=null) {
            vo.setTags(getMessageTemplateTagVOList(vo.getId()));

            List list = new ArrayList(1);
            list.add(vo);
            setTemplateData(list);
        }
        return vo;
    }


    @Override
    public List<MessageTypeVO> getMessageTypeVOList() {
        QMessageType e=QMessageType.messageType;
        return queryList(e,e.id.gt(0),new MessageTypeVOConver());
    }

    private void setTemplateData(List<MessageTypeVO> list){

        List<Long> tids=new ArrayList<>();
        for (MessageTypeVO vo:list){
            if(vo.getMsgTemplateId()!=null)
                tids.add(vo.getMsgTemplateId());
            if(vo.getEmailTemplateId()!=null)
                tids.add(vo.getEmailTemplateId());
            if(vo.getSmsTemplateId()!=null)
                tids.add(vo.getSmsTemplateId());
            if(vo.getPushTemplateId()!=null)
                tids.add(vo.getPushTemplateId());
            if(vo.getMpTemplateId()!=null)
                tids.add(vo.getMpTemplateId());
        }

        List<MessageTemplateVO> ts=getMessageTemplateVOListByIdIn(tids);

        Map<Long, MessageTemplateVO> tsmap=ts.stream().collect(Collectors.toMap(t->t.getId(), t->t));


        for (MessageTypeVO vo:list){
            if(vo.getMsgTemplateId()!=null)
                vo.setSmsTemplate(tsmap.get(vo.getSmsTemplateId()));
            if(vo.getEmailTemplateId()!=null)
                vo.setEmailTemplate(tsmap.get(vo.getEmailTemplateId()));
            if(vo.getSmsTemplateId()!=null)
                vo.setSmsTemplate(tsmap.get(vo.getSmsTemplateId()));
            if(vo.getPushTemplateId()!=null)
                vo.setPushTemplate(tsmap.get(vo.getPushTemplateId()));
            if(vo.getMpTemplateId()!=null)
                vo.setMpTemplate(tsmap.get(vo.getMpTemplateId()));
        }

    }


    @CacheEvict(value = "cache_message_type", allEntries = true)
    @Transactional
    @Override
    public MessageTemplate saveMessageTemplate(MessageTemplateDTO request) {
        MessageTemplate template = request.getId()==null?null:messageTemplateDao.getOne(request.getId());

        if(template==null){
            template=new MessageTemplate();
            template.setType(messageTypeDao.getOne(request.getTypeId()));
            template.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,template);

        template.setUpdateTime(new Date());

        template = messageTemplateDao.save(template);
        return template;
    }

    @CacheEvict(value = "cache_message_type", allEntries = true)
    @Transactional
    @Override
    public void deleteMessageTemplateById(Long id) {
        MessageTemplate template=messageTemplateDao.getOne(id);
        if(template.getSendType()== SendType.MSG.getType()){
            messageTypeDao.setMsgTemplateNullByTemplate(template);
        }else if(template.getSendType()== SendType.EMAIL.getType()){
            messageTypeDao.setEmailTemplateNullByTemplate(template);
        }else if(template.getSendType()== SendType.SMS.getType()){
            messageTypeDao.setSmsTemplateNullByTemplate(template);
        }else if(template.getSendType()== SendType.PUSH.getType()){
            messageTypeDao.setPushTemplateNullByTemplate(template);
        }else if(template.getSendType()== SendType.MP.getType()){
            messageTypeDao.setMpTemplateNullByTemplate(template);
        }
        messageTemplateDao.delete(template);
    }

    @Override
    public MessageTemplate getMessageTemplateById(Long id) {
        return messageTemplateDao.findById(id).orElse(null);
    }

    @Override
    public List<MessageTemplate> getMessageTemplateList(Long typeId) {
        return messageTemplateDao.findAllByTypeId(typeId);
    }

    @Override
    public List<MessageTemplate> getMessageTemplateList(Long typeId, int sendType) {
        return messageTemplateDao.findAllByTypeIdAndSendType(typeId, sendType);
    }



    @Override
    public List<MessageTemplateVO> getMessageTemplateVOListByIdIn(Collection<Long> idIn) {
        QMessageTemplate e=QMessageTemplate.messageTemplate;
        List<MessageTemplateVO> list=queryList(e,e.id.in(idIn),messageTemplateVOConver);
        return list;
    }

    @Override
    public MessageTemplateVO getMessageTemplateVOById(Long id) {
        QMessageTemplate e=QMessageTemplate.messageTemplate;
        MessageTemplateVO vo=queryOne(e,e.id.eq(id),messageTemplateVOConver);
        return vo;
    }

    @Override
    public List<MessageTemplateTagVO> getMessageTemplateTagVOList(Long typeId) {
        QMessageTemplateTag e=QMessageTemplateTag.messageTemplateTag;
        return queryList(e,e.type.id.eq(typeId),messageTemplateTagVOConver);
    }

    @Override
    public Page<MessageTypeVO> getMessageTypeVOPage(Pageable pageable) {
        QMessageType e=QMessageType.messageType;
        Predicate p=null;
        Page<MessageTypeVO> page=queryPage(e,p,messageTypeVOConver,pageable);
        return page;
    }

    @Override
    public Page<MessageTemplateVO> getMessageTemplateVOPage(GetMessageTemplateListDTO request, Pageable pageable) {
        QMessageTemplate e=QMessageTemplate.messageTemplate;
        Predicate p=null;
        if(request.getTypeId()!=null){
            p=e.type.id.eq(request.getTypeId()).and(p);
        }
        Page<MessageTemplateVO> page=queryPage(e,p,messageTemplateVOConver,pageable);
        return page;
    }
}
