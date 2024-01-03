package com.outmao.ebs.user.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.user.dao.UserCertificationDao;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.domain.UserCertificationDomain;
import com.outmao.ebs.user.domain.conver.UserCertificationVOConver;
import com.outmao.ebs.user.dto.GetUserCertificationListDTO;
import com.outmao.ebs.user.dto.SetUserCertificationStatusDTO;
import com.outmao.ebs.user.dto.UserCertificationDTO;
import com.outmao.ebs.user.entity.QUserCertification;
import com.outmao.ebs.user.entity.UserCertification;
import com.outmao.ebs.user.vo.UserCertificationVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.Date;


@Component
public class UserCertificationDomainImpl extends BaseDomain implements UserCertificationDomain {


    @Autowired
    private UserCertificationDao userCertificationDao;

    @Autowired
    private UserDao userDao;


    private UserCertificationVOConver userCertificationVOConver=new UserCertificationVOConver();

    @Transactional
    @Override
    public UserCertification saveUserCertification(UserCertificationDTO request) {
        UserCertification certification=userCertificationDao.findByUserId(request.getUserId());
        if(certification==null){
            certification=new UserCertification();
            certification.setUser(userDao.getOne(request.getUserId()));
            certification.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,certification);
        certification.setUpdateTime(new Date());
        certification.setKeyword(getKeyword(certification));

        certification.setStatus(Status.NotAudit.getStatus());
        certification.setStatusRemark(Status.NotAudit.getStatusRemark());

        userCertificationDao.save(certification);

        return certification;
    }


    public String getKeyword(UserCertification data){

        StringBuffer s=new StringBuffer();

        s.append(data.getName());

        s.append(" "+data.getCertificateNumber());

        return s.toString();

    }

    @Transactional
    @Override
    public void deleteUserCertificationById(Long id) {
        userCertificationDao.deleteById(id);
    }


    @Override
    public UserCertification getUserCertificationById(Long id) {
        return userCertificationDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public UserCertification setUserCertificationStatus(SetUserCertificationStatusDTO request) {
        UserCertification certification=userCertificationDao.getOne(request.getId());
        BeanUtils.copyProperties(request,certification);
        userCertificationDao.save(certification);
        return certification;
    }

    @Override
    public UserCertificationVO getUserCertificationVOByUserId(Long userId) {
        QUserCertification e=QUserCertification.userCertification;
        return queryOne(e,e.user.id.eq(userId),userCertificationVOConver);
    }

    @Override
    public Page<UserCertificationVO> getUserCertificationVOPage(GetUserCertificationListDTO request, Pageable pageable) {

        QUserCertification e=QUserCertification.userCertification;

        Predicate p=null;

        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        Page<UserCertificationVO> page=queryPage(e,p,userCertificationVOConver,pageable);

        return page;

    }



}
