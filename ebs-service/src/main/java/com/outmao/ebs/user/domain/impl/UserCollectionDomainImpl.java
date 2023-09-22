package com.outmao.ebs.user.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.user.dao.UserCollectionDao;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.domain.UserCollectionDomain;
import com.outmao.ebs.user.dto.UserCollectionDTO;
import com.outmao.ebs.user.entity.UserCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class UserCollectionDomainImpl extends BaseDomain implements UserCollectionDomain {

    @Autowired
    private UserCollectionDao userCollectionDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserCollection saveUserCollection(UserCollectionDTO request) {
        UserCollection uc= request.getId()==null?userCollectionDao.findByUserIdAndToId(request.getUserId(), request.getToId())
                :userCollectionDao.getOne(request.getId());
        if(uc==null){
            uc=new UserCollection();
            uc.setUser(userDao.getOne(request.getUserId()));
            uc.setTo(userDao.getOne(request.getToId()));
            uc.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,uc);
        uc.setUpdateTime(new Date());
        userCollectionDao.save(uc);
        return uc;
    }

    @Override
    public UserCollection getUserCollection(Long userId, Long toId) {
        return userCollectionDao.findByUserIdAndToId(userId,toId);
    }


}
