package com.outmao.ebs.user.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.user.dao.UserLevelDao;
import com.outmao.ebs.user.domain.UserLevelDomain;
import com.outmao.ebs.user.dto.UserLevelDTO;
import com.outmao.ebs.user.entity.UserLevel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
public class UserLevelDomainImpl extends BaseDomain implements UserLevelDomain {

    @Autowired
    private UserLevelDao userLevelDao;

    @CacheEvict(value = "cache_user_level", allEntries = true)
    @Transactional
    @Override
    public UserLevel saveUserLevel(UserLevelDTO request) {
        UserLevel level= request.getId()==null?null: userLevelDao.getOne(request.getId());
        if(level==null){
            level=new UserLevel();
            level.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,level);
        userLevelDao.save(level);
        return level;
    }

    @Override
    public UserLevel getUserLevelByValue(int value) {
        return userLevelDao.findByStartLessThanEqualAndEndGreaterThanEqual(value);
    }

    @Cacheable(value = "cache_user_level", key = "method.name")
    @Override
    public List<UserLevel> getUserLevelList() {
        return userLevelDao.findAll(Sort.by(Sort.Direction.ASC,"level"));
    }

}
