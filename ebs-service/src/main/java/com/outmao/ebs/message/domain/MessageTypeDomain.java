package com.outmao.ebs.message.domain;

import com.outmao.ebs.message.dto.GetMessageTemplateListDTO;
import com.outmao.ebs.message.dto.MessageTemplateDTO;
import com.outmao.ebs.message.dto.MessageTypeDTO;
import com.outmao.ebs.message.entity.MessageTemplate;
import com.outmao.ebs.message.entity.MessageType;
import com.outmao.ebs.message.vo.MessageTemplateTagVO;
import com.outmao.ebs.message.vo.MessageTemplateVO;
import com.outmao.ebs.message.vo.MessageTypeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface MessageTypeDomain {


    public MessageType saveMessageType(MessageTypeDTO request);
    public void deleteMessageTypeById(Long id);
    public MessageType getMessageTypeById(Long id);
    public MessageType getMessageTypeByName(String name);
    public List<MessageType> getMessageTypeList();


    public MessageTypeVO getMessageTypeVOByName(String name);
    public MessageTypeVO getMessageTypeVOById(Long id);
    public List<MessageTypeVO> getMessageTypeVOList();


    public MessageTemplate saveMessageTemplate(MessageTemplateDTO request);
    public void deleteMessageTemplateById(Long id);
    public MessageTemplate getMessageTemplateById(Long id);
    public List<MessageTemplate> getMessageTemplateList(Long typeId);
    public List<MessageTemplate> getMessageTemplateList(Long typeId, int sendType);

    public MessageTemplateVO getMessageTemplateVOById(Long id);

    public List<MessageTemplateVO> getMessageTemplateVOListByIdIn(Collection<Long> idIn);

    public List<MessageTemplateTagVO> getMessageTemplateTagVOList(Long typeId);

    public Page<MessageTypeVO> getMessageTypeVOPage(Pageable pageable);

    public Page<MessageTemplateVO> getMessageTemplateVOPage(GetMessageTemplateListDTO request, Pageable pageable);


}
