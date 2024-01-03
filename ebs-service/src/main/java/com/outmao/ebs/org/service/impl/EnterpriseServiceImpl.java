package com.outmao.ebs.org.service.impl;



import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.org.domain.EnterpriseDomain;
import com.outmao.ebs.org.dto.EnterpriseDTO;
import com.outmao.ebs.data.dto.GetEnterpriseListDTO;
import com.outmao.ebs.org.entity.enterprise.Enterprise;
import com.outmao.ebs.org.service.EnterpriseService;
import com.outmao.ebs.org.vo.EnterpriseVO;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnterpriseServiceImpl extends BaseService implements EnterpriseService {

    @Autowired
    private EnterpriseDomain enterpriseDomain;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public Enterprise saveEnterprise(EnterpriseDTO request) {

        Enterprise enterprise= enterpriseDomain.saveEnterprise(request);

        updateUser(enterprise);

        return enterprise;
    }

    @Transactional
    @Override
    public void deleteEnterpriseById(Long id) {
        Enterprise enterprise=enterpriseDomain.getEnterpriseById(id);
        enterprise.setStatus(Status.DELETED.getStatus());
        updateUser(enterprise);
        enterpriseDomain.deleteEnterpriseById(id);
    }

    @Transactional
    @Override
    public Enterprise setEnterpriseStatus(Long id, int status, String statusRemark) {
        Enterprise enterprise= enterpriseDomain.setEnterpriseStatus(id,status,statusRemark);
        updateUser(enterprise);
        return enterprise;
    }

    private void updateUser(Enterprise enterprise){
        userService.setUserEntVerified(
                enterprise.getUser().getId(),
                enterprise.getStatus()== Status.NORMAL.getStatus(),
                enterprise.getId(),
                enterprise.getEnterpriseName()
        );
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
