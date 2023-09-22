package com.outmao.ebs.message.dao;


import com.outmao.ebs.message.entity.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MessageTemplateDao extends JpaRepository<MessageTemplate, Long> {

    public void deleteAllByTypeId(Long typeId);

    public List<MessageTemplate> findAllByTypeId(Long typeId);

    public List<MessageTemplate> findAllByTypeIdAndSendType(Long typeId, int sendType);

    public List<MessageTemplate> findAllByIdIn(Collection<Long> idIn);

}
