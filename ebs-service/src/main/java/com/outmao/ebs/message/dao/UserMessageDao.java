package com.outmao.ebs.message.dao;


import com.outmao.ebs.message.entity.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageDao extends JpaRepository<UserMessage, Long> {

    public void deleteAllByMessageId(Long messageId);

}
