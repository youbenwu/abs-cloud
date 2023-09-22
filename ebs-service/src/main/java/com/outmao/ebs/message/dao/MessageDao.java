package com.outmao.ebs.message.dao;


import com.outmao.ebs.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageDao extends JpaRepository<Message, Long> {

}
