package com.outmao.ebs.user.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.user.dao.UserAddressDao;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.domain.UserAddressDomain;
import com.outmao.ebs.user.dto.GetUserAddressListDTO;
import com.outmao.ebs.user.dto.UserAddressDTO;
import com.outmao.ebs.user.entity.QUserAddress;
import com.outmao.ebs.user.entity.UserAddress;
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
public class UserAddressDomainImpl extends BaseDomain implements UserAddressDomain {

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserAddress saveUserAddres(UserAddressDTO request) {
        UserAddress addr= request.getId()==null?null:userAddressDao.getOne(request.getId());
        if (addr == null) {
            addr = new UserAddress();
            addr.setUser(userDao.getOne(request.getUserId()));
            addr.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,addr);

        addr.setUpdateTime(addr.getCreateTime());

        userAddressDao.save(addr);

        if(addr.isDef()){
            QUserAddress e = QUserAddress.userAddress;
            QF.update(e).set(e.def, false).where(
                    e.user.id.eq(request.getUserId()).and(e.id.ne(addr.getId())).and(e.def.isTrue())
            ).execute();
        }


        return addr;
    }

    @Transactional
    @Override
    public void deleteUserAddressById(Long id) {
        userAddressDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteUserAddressByIdIn(List<Long> idIn) {
        userAddressDao.deleteAllByIdIn(idIn);
    }

    @Override
    public UserAddress getUserAddressById(Long id) {
        UserAddress addr = userAddressDao.findById(id).orElse(null);
        return addr;
    }

    @Override
    public UserAddress getDefaultUserAddressByUserId(Long userId) {
        QUserAddress e=QUserAddress.userAddress;
        UserAddress address=QF.select(e).from(e).where(e.user.id.eq(userId)).orderBy(e.def.desc()).fetchFirst();
        return address;
    }


    @Override
    public Page<UserAddress> getUserAddressPage(GetUserAddressListDTO request, Pageable pageable) {
        QUserAddress e=QUserAddress.userAddress;
        Predicate p=null;
        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId());
        }
        Page<UserAddress> page = userAddressDao.findAll(p, pageable);
        return page;
    }



}
