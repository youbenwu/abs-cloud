package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dao.OrgDao;
import com.outmao.ebs.org.domain.OrgDomain;
import com.outmao.ebs.org.domain.conver.CacheOrgVOConvert;
import com.outmao.ebs.org.domain.conver.OrgVOConvert;
import com.outmao.ebs.org.dto.GetOrgListDTO;
import com.outmao.ebs.org.dto.OrgDTO;
import com.outmao.ebs.org.dto.RegisterOrgDTO;
import com.outmao.ebs.org.dto.SetOrgStatusDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.entity.OrgContact;
import com.outmao.ebs.org.entity.QOrg;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class OrgDomainImpl extends BaseDomain implements OrgDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgDao orgDao;

    private OrgVOConvert orgVOConvert=new OrgVOConvert();

    private CacheOrgVOConvert cacheOrgVOConvert=new CacheOrgVOConvert();


    @Transactional()
    @Override
    public Org registerOrg(RegisterOrgDTO request) {
        Org org=new Org();
        org.setUser(userDao.getOne(request.getUserId()));
        if(request.getParentId()!=null) {
            Org parent=orgDao.findByIdForUpdate(request.getParentId());
            org.setParent(parent);
            org.setLevel(parent.getLevel()+1);
            if(parent.isLeaf()){
                parent.setLeaf(false);
            }
        }
        BeanUtils.copyProperties(request,org,"contact");

        OrgContact contact=org.getContact();
        if(contact==null){
            contact=new OrgContact();
            org.setContact(contact);
        }
        if(request.getContact()!=null) {
            BeanUtils.copyProperties(request.getContact(), contact);
        }

        org.setCreateTime(new Date());
        org.setUpdateTime(org.getCreateTime());

        orgDao.save(org);


        return org;
    }

    @Transactional()
    @Override
    public Org saveOrg(OrgDTO request) {

        Org org= orgDao.findByIdForUpdate(request.getId());

        BeanUtils.copyProperties(request,org);

        org.setUpdateTime(new Date());

        org.setKeyword(getKeyword(org));

        orgDao.save(org);


        return org;
    }


    private String getKeyword(Org data){
        StringBuffer s=new StringBuffer();
        if(!StringUtils.isEmpty(data.getName())){
            s.append(" "+data.getName());
        }
        if(!StringUtils.isEmpty(data.getIntro())){
            s.append(" "+data.getIntro());
        }
        if(data.getContact()!=null){
            s.append(" "+data.getContact().toString());
        }
        return s.toString().trim();
    }


    @Transactional()
    @Override
    public Org setOrgStatus(SetOrgStatusDTO request) {
        Org org=orgDao.findByIdForUpdate(request.getId());
        org.setStatus(request.getStatus());
        org.setStatusRemark(request.getStatusRemark());
        orgDao.save(org);

        return org;
    }

    @Transactional()
    @Override
    public void deleteOrgById(Long id) {
        Org org=orgDao.findByIdForUpdate(id);
        if(!org.isLeaf()){
            throw new BusinessException("请先删除下级组织");
        }
        if(org.getParent()!=null) {
            Org parent = orgDao.findByIdForUpdate(org.getParent().getId());
            if (parent.getChildren().size() == 1) {
                parent.setLeaf(true);
            }
        }
        orgDao.delete(org);
    }


    @Override
    public Org getOrgById(Long id) {
        return orgDao.findById(id).orElse(null);
    }

    @Override
    public Org getOrgByTargetId(Long targetId) {
        return orgDao.findByTargetId(targetId);
    }

    @Override
    public Org getOrg() {
        return orgDao.findByType(Org.TYPE_SYSTEM);
    }

    @Override
    public List<Org> getOrgListByIdIn(Collection<Long> idIn) {
        return orgDao.findAllByIdIn(idIn);
    }

    @Override
    public Long getOrgIdByTargetId(Long targetId) {
        return orgDao.findIdByTargetId(targetId);
    }

    @Override
    public OrgVO getOrgVOById(Long id) {
        QOrg e=QOrg.org;
        return queryOne(e,e.id.eq(id),orgVOConvert);
    }

    @Override
    public List<OrgVO> getOrgVOListByIdIn(Collection<Long> idIn) {
        QOrg e=QOrg.org;
        return queryList(e,e.id.in(idIn),orgVOConvert);
    }

    @Override
    public Page<OrgVO> getOrgVOPage(GetOrgListDTO request, Pageable pageable) {
        QOrg e=QOrg.org;
        Predicate p=null;
        if(request.getKeyword()!=null){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId()).and(p);
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        Page<OrgVO> page=queryPage(e,p,orgVOConvert,pageable);

        return page;
    }


    @Cacheable(value = "cache_org", key = "#id",condition = "#id!=null",unless = "#result == null")
    @Override
    public CacheOrgVO getCacheOrgVOById(Long id) {
        QOrg e=QOrg.org;
        CacheOrgVO vo=queryOne(e,e.id.eq(id),cacheOrgVOConvert);
        if(vo!=null&&vo.getParentId()!=null){
            vo.setParent(getCacheOrgVOById(vo.getParentId()));
        }
        return vo;
    }

    @Cacheable(value = "cache_org", key = "#methodName",unless = "#result == null")
    @Override
    public CacheOrgVO getCacheOrgVO() {
        QOrg e=QOrg.org;
        CacheOrgVO vo=queryOne(e,e.type.eq(Org.TYPE_SYSTEM),cacheOrgVOConvert);
        return vo;
    }


}
