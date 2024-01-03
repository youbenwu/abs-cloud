package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.user.domain.UserCertificationDomain;
import com.outmao.ebs.user.dto.GetUserCertificationListDTO;
import com.outmao.ebs.user.dto.SetUserCertificationStatusDTO;
import com.outmao.ebs.user.dto.UserCertificationDTO;
import com.outmao.ebs.user.entity.UserCertification;
import com.outmao.ebs.user.service.UserCertificationService;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.user.vo.UserCertificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserCertificationServiceImpl extends BaseService implements UserCertificationService {


    @Autowired
    private UserCertificationDomain userCertificationDomain;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public UserCertification saveUserCertification(UserCertificationDTO request) {
        UserCertification certification=userCertificationDomain.saveUserCertification(request);
        updateUser(certification);
        return certification;
    }

    @Transactional
    @Override
    public void deleteUserCertificationById(Long id) {
        UserCertification certification=userCertificationDomain.getUserCertificationById(id);
        certification.setStatus(Status.DELETED.getStatus());
        updateUser(certification);
        userCertificationDomain.deleteUserCertificationById(id);
    }

    @Transactional
    @Override
    public UserCertification setUserCertificationStatus(SetUserCertificationStatusDTO request) {
        UserCertification certification= userCertificationDomain.setUserCertificationStatus(request);
        updateUser(certification);
        return certification;
    }

    private void updateUser(UserCertification certification){
        userService.setUserVerified(certification.getUser().getId(),certification.getStatus()==Status.NORMAL.getStatus(),certification.getName());
    }

    @Override
    public UserCertificationVO getUserCertificationVOByUserId(Long userId) {
        return userCertificationDomain.getUserCertificationVOByUserId(userId);
    }


    @Override
    public Page<UserCertificationVO> getUserCertificationVOPage(GetUserCertificationListDTO request, Pageable pageable) {
        return userCertificationDomain.getUserCertificationVOPage(request,pageable);
    }



}
