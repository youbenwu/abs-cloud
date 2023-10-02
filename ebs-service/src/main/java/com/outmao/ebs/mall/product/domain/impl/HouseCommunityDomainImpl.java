package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.product.dao.HouseCommunityDao;
import com.outmao.ebs.mall.product.domain.HouseCommunityDomain;
import com.outmao.ebs.mall.product.dto.GetHouseCommunityListDTO;
import com.outmao.ebs.mall.product.dto.HouseCommunityDTO;
import com.outmao.ebs.mall.product.entity.HouseCommunity;
import com.outmao.ebs.mall.product.entity.QHouseCommunity;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class HouseCommunityDomainImpl extends BaseDomain implements HouseCommunityDomain {


    @Autowired
    private HouseCommunityDao houseCommunityDao;


    @Transactional
    @Override
    public HouseCommunity saveHouseCommunity(HouseCommunityDTO request) {

        HouseCommunity community=request.getId()==null?null:houseCommunityDao.getOne(request.getId());

        if(community==null){
            community=new HouseCommunity();
            community.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,community);

        community.setUpdateTime(new Date());

        houseCommunityDao.save(community);

        return community;
    }

    @Transactional
    @Override
    public void deleteHouseCommunityById(Long id) {

        houseCommunityDao.deleteById(id);

    }

    @Override
    public HouseCommunity getHouseCommunityById(Long id) {
        return houseCommunityDao.findById(id).orElse(null);
    }

    @Override
    public Page<HouseCommunity> getHouseCommunityPage(GetHouseCommunityListDTO request, Pageable pageable) {


        QHouseCommunity e=QHouseCommunity.houseCommunity;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
           p=e.name.like("%"+request.getKeyword()+"%").and(p);
        }

        if(p==null)
            return houseCommunityDao.findAll(pageable);

        return houseCommunityDao.findAll(p,pageable);
    }



}
