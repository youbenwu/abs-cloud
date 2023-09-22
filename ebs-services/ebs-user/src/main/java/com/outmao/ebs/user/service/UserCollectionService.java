package com.outmao.ebs.user.service;

import com.outmao.ebs.user.dto.UserCollectionDTO;
import com.outmao.ebs.user.entity.UserCollection;

public interface UserCollectionService {

    // UserCollection
    public UserCollection saveUserCollection(UserCollectionDTO request);
    public UserCollection getUserCollection(Long userId, Long toId);

}
