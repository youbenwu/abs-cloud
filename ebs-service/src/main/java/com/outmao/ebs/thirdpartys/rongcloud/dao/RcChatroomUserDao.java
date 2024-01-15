package com.outmao.ebs.thirdpartys.rongcloud.dao;

import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RcChatroomUserDao extends JpaRepository<RcChatroomUser,Long> {

    public List<RcChatroomUser> findAllByUserId(Long userId);

    public boolean existsByChatroomIdAndUserId(Long chatroomId,Long userId);

    public void deleteByChatroomIdAndUserId(Long chatroomId,Long userId);

    public List<RcChatroomUser> findAllByChatroomIdIn(Collection<Long> chatroomIdIn);

}
