package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCollectionDao extends JpaRepository<UserCollection,Long> {

    public UserCollection findByUserIdAndToId(Long userId, Long toId);

}
