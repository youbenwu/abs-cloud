package com.outmao.ebs.org.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.OrgTypeDao;
import com.outmao.ebs.org.domain.OrgTypeDomain;
import com.outmao.ebs.org.domain.conver.OrgTypeVOConver;
import com.outmao.ebs.org.dto.OrgTypeDTO;
import com.outmao.ebs.org.entity.OrgType;
import com.outmao.ebs.org.entity.QOrgType;
import com.outmao.ebs.org.vo.OrgTypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class OrgTypeDomainImpl extends BaseDomain implements OrgTypeDomain {

    @Autowired
    private OrgTypeDao orgTypeDao;


    private OrgTypeVOConver orgTypeVOConver=new OrgTypeVOConver();


    @Transactional
    @Override
    public OrgType saveOrgType(OrgTypeDTO request) {
        OrgType type=orgTypeDao.findByOrgIdAndType(request.getOrgId(),request.getType());

        if(type==null){
            type=new OrgType();
            type.setCreateTime(new Date());
            BeanUtils.copyProperties(request,type);
            orgTypeDao.save(type);
        }

        return type;
    }

    @Transactional
    @Override
    public void deleteOrgTypeById(Long id) {
        orgTypeDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteOrgTypeByOrgId(Long orgId) {
        orgTypeDao.deleteAllByOrgId(orgId);
    }


    @Override
    public OrgType getOrgTypeByTargetId(Long targetId) {
        return orgTypeDao.findByTargetId(targetId);
    }

    @Override
    public List<OrgTypeVO> getOrgTypeVOListByOrgIdIn(Collection<Long> orgIdIn) {
        QOrgType e=QOrgType.orgType;
        return queryList(e,e.orgId.in(orgIdIn),orgTypeVOConver);
    }


}
