package com.outmao.ebs.user.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.dao.UserDataDao;
import com.outmao.ebs.user.domain.UserDataDomain;
import com.outmao.ebs.user.dto.GetUserDataListDTO;
import com.outmao.ebs.user.dto.UserDataDTO;
import com.outmao.ebs.user.entity.QUserData;
import com.outmao.ebs.user.entity.UserData;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class UserDataDomainImpl extends BaseDomain implements UserDataDomain {


    @Autowired
    private UserDataDao userDataDao;

    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public UserData saveUserData(UserDataDTO request) {
        UserData data=userDataDao.findByUserIdAndName(request.getUserId(),request.getName());

        if(data==null){
            data=new UserData();
            data.setUser(userDao.getOne(request.getUserId()));
            data.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,data);

        data.setUpdateTime(new Date());

        userDataDao.save(data);

        return data;
    }

    @Override
    public List<UserData> getUserDataList(GetUserDataListDTO request) {
        return userDataDao.findAllByUserIdAndType(request.getUserId(),request.getType());
    }

    @Override
    public UserData getUserData(Long userId,String name) {
        return userDataDao.findByUserIdAndName(userId,name);
    }

    @Override
    public Page<UserData> getUserDataPage(GetUserDataListDTO request, Pageable pageable) {
        QUserData e=QUserData.userData;
        Predicate p=null;
        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId());
        }
        if(StringUtil.isNotEmpty(request.getType())){
            p=e.type.eq(request.getType()).and(p);
        }
        return userDataDao.findAll(p,pageable);
    }


}
