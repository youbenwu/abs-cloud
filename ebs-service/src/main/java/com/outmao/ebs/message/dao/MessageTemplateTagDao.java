package com.outmao.ebs.message.dao;


import com.outmao.ebs.message.entity.MessageTemplateTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTemplateTagDao extends JpaRepository<MessageTemplateTag,Long> {

    public void deleteAllByTypeId(Long typeId);

    public List<MessageTemplateTag> findAllByTypeId(Long typeId);

    public MessageTemplateTag findByTypeIdAndValue(Long typeId, String value);

    public void deleteAllByTypeIdAndIdNotIn(Long typeId, List<Long> idNotIn);


}
