package com.outmao.ebs.org.service.impl;



import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.EnterpriseDomain;
import com.outmao.ebs.org.dto.EnterpriseDTO;
import com.outmao.ebs.data.dto.GetEnterpriseListDTO;
import com.outmao.ebs.org.entity.enterprise.Enterprise;
import com.outmao.ebs.org.service.EnterpriseService;
import com.outmao.ebs.org.vo.EnterpriseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseServiceImpl extends BaseService implements EnterpriseService {

    @Autowired
    EnterpriseDomain enterpriseDomain;


    @Override
    public Enterprise saveEnterprise(EnterpriseDTO request) {
        return enterpriseDomain.saveEnterprise(request);
    }

    @Override
    public Enterprise setEnterpriseStatus(Long id, int status, String statusRemark) {
        return enterpriseDomain.setEnterpriseStatus(id,status,statusRemark);
    }

    @Override
    public Enterprise getEnterpriseById(Long id) {
        return enterpriseDomain.getEnterpriseById(id);
    }

    @Override
    public List<Enterprise> getEnterpriseListByUserId(Long userId) {
        return enterpriseDomain.getEnterpriseListByUserId(userId);
    }

    @Override
    public Page<Enterprise> getEnterprisePage(GetEnterpriseListDTO request, Pageable pageable) {
        return enterpriseDomain.getEnterprisePage(request,pageable);
    }

    @Override
    public EnterpriseVO getEnterpriseVOById(Long id) {
        return enterpriseDomain.getEnterpriseVOById(id);
    }

    @Override
    public List<EnterpriseVO> getEnterpriseVOListByUserId(Long userId) {
        return enterpriseDomain.getEnterpriseVOListByUserId(userId);
    }

    @Override
    public Page<EnterpriseVO> getEnterpriseVOPage(GetEnterpriseListDTO request, Pageable pageable) {
        return enterpriseDomain.getEnterpriseVOPage(request,pageable);
    }

}
