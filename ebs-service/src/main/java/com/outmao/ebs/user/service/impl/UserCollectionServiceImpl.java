package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.UserCollectionDomain;
import com.outmao.ebs.user.dto.UserCollectionDTO;
import com.outmao.ebs.user.entity.UserCollection;
import com.outmao.ebs.user.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCollectionServiceImpl extends BaseService implements UserCollectionService {

    @Autowired
    private UserCollectionDomain userCollectionDomain;

    @Override
    public UserCollection saveUserCollection(UserCollectionDTO request) {
        return userCollectionDomain.saveUserCollection(request);
    }

    @Override
    public UserCollection getUserCollection(Long userId, Long toId) {
        return userCollectionDomain.getUserCollection(userId,toId);
    }




}
