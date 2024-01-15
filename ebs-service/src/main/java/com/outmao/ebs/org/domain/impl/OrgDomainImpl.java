package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dao.OrgDao;
import com.outmao.ebs.org.dao.OrgParentDao;
import com.outmao.ebs.org.domain.OrgDomain;
import com.outmao.ebs.org.domain.conver.CacheOrgVOConvert;
import com.outmao.ebs.org.domain.conver.OrgVOConvert;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.user.common.annotation.AutoRegisterUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.*;


@Component
public class OrgDomainImpl extends BaseDomain implements OrgDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private OrgParentDao orgParentDao;

    private OrgVOConvert orgVOConvert=new OrgVOConvert();

    private CacheOrgVOConvert cacheOrgVOConvert=new CacheOrgVOConvert();


    @Transactional()
    @AutoRegisterUser
    @Override
    public Org registerOrg(RegisterOrgDTO request) {

        Org org=new Org();

        org.setContact(new OrgContact());

        org.setUser(userDao.getOne(request.getUserId()));

        if(request.getParentId()!=null) {
            Org parent=orgDao.findByIdLock(request.getParentId());
            org.setParent(parent);
            org.setLevel(parent.getLevel()+1);
            if(parent.isLeaf()){
                parent.setLeaf(false);
            }
        }

        BeanUtils.copyProperties(request,org,"contact","id");

        if(request.getContact()!=null) {
            BeanUtils.copyProperties(request.getContact(), org.getContact(),"id");
        }

        org.setCreateTime(new Date());

        org.setUpdateTime(org.getCreateTime());

        org.setKeyword(getKeyword(org));

        orgDao.save(org);

        return org;
    }

    @Transactional()
    @Override
    public Org saveOrg(OrgDTO request) {

        Org org= orgDao.findByIdLock(request.getId());

        BeanUtils.copyProperties(request,org,"contact","id");

        if(request.getContact()!=null) {
            BeanUtils.copyProperties(request.getContact(), org.getContact(),"id");
        }

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
        Org org=orgDao.findByIdLock(request.getId());
        org.setStatus(request.getStatus());
        org.setStatusRemark(request.getStatusRemark());
        orgDao.save(org);

        return org;
    }

    @Transactional()
    @Override
    public void deleteOrgById(Long id) {
        orgParentDao.deleteAllByOrgId(id);
        Org org=orgDao.findByIdLock(id);
        if(!org.isLeaf()){
            throw new BusinessException("请先删除下级组织");
        }
        if(org.getParent()!=null) {
            Org parent = orgDao.findByIdLock(org.getParent().getId());
            if (parent.getChildren().size() == 1) {
                parent.setLeaf(true);
            }
        }
        orgDao.delete(org);
    }

    @CacheEvict(value = "cache_org", key = "#id")
    @Transactional()
    @Override
    public OrgParent saveOrgParent(OrgParentDTO request) {
        OrgParent parent=orgParentDao.findByOrgIdAndParentId(request.getOrgId(),request.getParentId());
        if(parent==null){
            parent=new OrgParent();
            BeanUtils.copyProperties(request,parent);
            orgParentDao.save(parent);
        }
        return parent;
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
    public OrgVO getOrgVOById(Long id) {
        QOrg e=QOrg.org;
        return queryOne(e,e.id.eq(id),orgVOConvert);
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
            QOrgType t=QOrgType.orgType;
            p=e.id.eq(t.orgId).and(e.type.eq(request.getType()).or(t.type.eq(request.getType()))).and(p);
        }

        Page<OrgVO> page=queryPage(e,p,orgVOConvert,pageable);

        return page;
    }

    @Override
    public List<OrgVO> getOrgVOListByIdIn(Collection<Long> idIn) {
        QOrg e=QOrg.org;
        return queryList(e,e.id.in(idIn),orgVOConvert);
    }

    @Cacheable(value = "cache_org", key = "#id",condition = "#id!=null",unless = "#result == null")
    @Override
    public CacheOrgVO getCacheOrgVOById(Long id) {
        QOrg e=QOrg.org;
        CacheOrgVO vo=queryOne(e,e.id.eq(id),cacheOrgVOConvert);
        if(vo!=null&&vo.getParentId()!=null){
            vo.setParent(getCacheOrgVOById(vo.getParentId()));
        }
        if(vo!=null){
            vo.setParents(orgParentDao.findAllParentIdByOrgId(id));
        }
        return vo;
    }

    @Cacheable(value = "cache_org", key = "#root.methodName",unless = "#result == null")
    @Override
    public CacheOrgVO getCacheOrgVO() {
        QOrg e=QOrg.org;
        CacheOrgVO vo=queryOne(e,e.type.eq(OrgType.TYPE_SYSTEM),cacheOrgVOConvert);
        return vo;
    }


}
