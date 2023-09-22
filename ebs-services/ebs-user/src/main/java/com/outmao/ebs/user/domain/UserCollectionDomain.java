package com.outmao.ebs.user.domain;

import com.outmao.ebs.user.dto.UserCollectionDTO;
import com.outmao.ebs.user.entity.UserCollection;

public interface UserCollectionDomain {


    public UserCollection saveUserCollection(UserCollectionDTO request);

    public UserCollection getUserCollection(Long userId, Long toId);




}
