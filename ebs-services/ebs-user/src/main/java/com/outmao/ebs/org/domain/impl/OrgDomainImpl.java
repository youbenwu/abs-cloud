package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dao.OrgDao;
import com.outmao.ebs.org.domain.OrgDomain;
import com.outmao.ebs.org.domain.conver.OrgVOConvert;
import com.outmao.ebs.org.domain.conver.SecurityOrgConver;
import com.outmao.ebs.org.dto.GetOrgListDTO;
import com.outmao.ebs.org.dto.OrgDTO;
import com.outmao.ebs.org.dto.RegisterOrgDTO;
import com.outmao.ebs.org.dto.SetOrgStatusDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.entity.OrgContact;
import com.outmao.ebs.org.entity.QOrg;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.security.vo.SecurityOrg;
import com.outmao.ebs.sys.entity.QSys;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class OrgDomainImpl extends BaseDomain implements OrgDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgDao orgDao;

    private OrgVOConvert orgVOConvert=new OrgVOConvert();

    @Autowired
    private SecurityOrgConver securityOrgConver;

    private Org sysOrg;




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

        if(org.getType()== Org.TYPE_SYSTEM){
            sysOrg=org;
        }

        return org;
    }

    @CacheEvict(value = "cache_org", key = "#request.id")
    @Transactional()
    @Override
    public Org saveOrg(OrgDTO request) {

        Org org= orgDao.findByIdForUpdate(request.getId());

        BeanUtils.copyProperties(request,org);

        org.setUpdateTime(new Date());

        orgDao.save(org);

        if(org.getType()== Org.TYPE_SYSTEM){
            sysOrg=org;
        }

        return org;
    }

    @CacheEvict(value = "cache_org", key = "#request.id")
    @Transactional()
    @Override
    public Org setOrgStatus(SetOrgStatusDTO request) {
        Org org=orgDao.findByIdForUpdate(request.getId());
        org.setStatus(request.getStatus());
        org.setStatusRemark(request.getStatusRemark());
        orgDao.save(org);

        if(org.getType()== Org.TYPE_SYSTEM){
            sysOrg=org;
        }

        return org;
    }

    @CacheEvict(value = "cache_org", key = "#request.id")
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
        if(sysOrg==null){
            sysOrg=orgDao.findByType(Org.TYPE_SYSTEM);
        }
        return sysOrg;
    }

    @Override
    public List<Org> getOrgListByIdIn(Collection<Long> idIn) {
        return orgDao.findAllByIdIn(idIn);
    }

    @Override
    public Long getOrgIdByTargetId(Long targetId) {
        return orgDao.findIdByTargetId(targetId);
    }

    @Cacheable(value = "cache_org", key = "#id",condition = "#id!=null",unless = "#result == null")
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
            p=e.name.like("%"+request.getKeyword()+"%");
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

    @Override
    public List<SecurityOrg> getSecurityOrgList(Collection<Long> orgIdIn, Long sysId) {
        QOrg e=QOrg.org;
        List<SecurityOrg> list=queryList(e,e.id.in(orgIdIn),securityOrgConver);

        if(list.isEmpty())
            return list;


        QSys s=QSys.sys;

        if(sysId!=null){
            Tuple t=QF.select(s.id,s.name,s.type).from(s).where(s.id.eq(sysId)).fetchOne();
            list.forEach(org->{
                if(t.get(s.type).equals(org.getOrgType())){
                    org.setSysId(t.get(s.id));
                    org.setSysName(t.get(s.name));
                }
            });
        }else {
            List<Tuple> ss = QF.select(s.id, s.name, s.type).from(s).where(s.type.in(list.stream().map(t -> t.getOrgType()).collect(Collectors.toList()))).fetch();
            Map<Integer, Tuple> sMap = ss.stream().collect(Collectors.toMap(t -> t.get(s.type), t -> t));
            list.forEach(org -> {
                Tuple t = sMap.get(org.getOrgType());
                if (t != null) {
                    org.setSysId(t.get(s.id));
                    org.setSysName(t.get(s.name));
                }
            });
        }

        return list.stream().filter(t->t.getSysId()!=null).collect(Collectors.toList());
    }
}
