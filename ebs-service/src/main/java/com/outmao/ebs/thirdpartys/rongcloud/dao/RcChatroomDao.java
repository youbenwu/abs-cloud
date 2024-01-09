package com.outmao.ebs.thirdpartys.rongcloud.dao;

import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RcChatroomDao extends JpaRepository<RcChatroom,Long> {

    public RcChatroom findByChatroomId(String chatroomId);

    public RcChatroom findByRtcroomId(String rtcroomId);

    public List<RcChatroom> findAllByGroupIdOrderByUpdateTimeDesc(String groupId);

}
