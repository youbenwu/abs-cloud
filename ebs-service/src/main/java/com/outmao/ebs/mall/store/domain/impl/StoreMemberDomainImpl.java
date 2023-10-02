package com.outmao.ebs.mall.store.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.store.dao.StoreDao;
import com.outmao.ebs.mall.store.dao.StoreMemberDao;
import com.outmao.ebs.mall.store.domain.StoreMemberDomain;
import com.outmao.ebs.mall.store.domain.conver.StoreMemberVOConver;
import com.outmao.ebs.mall.store.dto.GetStoreMemberListDTO;
import com.outmao.ebs.mall.store.dto.StoreMemberDTO;
import com.outmao.ebs.mall.store.entity.QStoreMember;
import com.outmao.ebs.mall.store.entity.StoreMember;
import com.outmao.ebs.mall.store.vo.StoreMemberVO;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class StoreMemberDomainImpl extends BaseDomain implements StoreMemberDomain {

    @Autowired
    private StoreMemberDao storeMemberDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private StoreMemberVOConver storeMemberVOConver;

    @Transactional
    @Override
    public StoreMember saveStoreMember(StoreMemberDTO request) {
        StoreMember member=request.getId()!=null?storeMemberDao.getOne(request.getId()):storeMemberDao.findByStoreIdAndUserId(request.getStoreId(),request.getUserId());
        if(member==null){
            member=new StoreMember();
            member.setStore(storeDao.getOne(request.getStoreId()));
            member.setUser(userDao.getOne(request.getUserId()));
            member.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,member);
        member.setUpdateTime(new Date());
        storeMemberDao.save(member);

        return member;
    }

    @Transactional
    @Override
    public void deleteStoreMemberById(Long id) {
        storeMemberDao.deleteById(id);
    }

    @Override
    public StoreMember getStoreMember(Long storeId, Long userId) {
        return storeMemberDao.findByStoreIdAndUserId(storeId,userId);
    }


    @Override
    public Page<StoreMemberVO> getStoreMemberVOPage(GetStoreMemberListDTO request, Pageable pageable) {
        QStoreMember e=QStoreMember.storeMember;
        Predicate p=null;
        if(request.getStoreId()!=null){
            p=e.store.id.eq(request.getStoreId());
        }
        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId()).and(p);
        }
        if(StringUtil.isNotEmpty(request.getJob())){
            p=e.job.eq(request.getJob()).and(p);
        }

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.name.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<StoreMemberVO> page=queryPage(e,p,storeMemberVOConver,pageable);

        return page;
    }



}
