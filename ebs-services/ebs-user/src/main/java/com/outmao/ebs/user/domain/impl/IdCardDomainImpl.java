package com.outmao.ebs.user.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.user.common.constant.CertificationStatus;
import com.outmao.ebs.user.dao.IdCardDao;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.domain.IdCardDomain;
import com.outmao.ebs.user.domain.conver.IdCardVOConver;
import com.outmao.ebs.user.dto.GetIdCardListDTO;
import com.outmao.ebs.user.dto.IdCardDTO;
import com.outmao.ebs.user.dto.SetIdCardStatusDTO;
import com.outmao.ebs.user.entity.IdCard;
import com.outmao.ebs.user.entity.QIdCard;
import com.outmao.ebs.user.vo.IdCardVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class IdCardDomainImpl extends BaseDomain implements IdCardDomain {


    @Autowired
    private IdCardDao idCardDao;

    @Autowired
    private UserDao userDao;

    private IdCardVOConver idCardVOConver=new IdCardVOConver();



    @Transactional
    @Override
    public IdCard saveIdCard(IdCardDTO request) {
        IdCard idCard = request.getId()!=null?idCardDao.getOne(request.getId()):
                idCardDao.findByUserId(request.getUserId());
        if (idCard == null) {
            idCard = new IdCard();
            idCard.setUser(userDao.getOne(request.getUserId()));
            idCard.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,idCard);
        idCard.setUpdateTime(new Date());
        idCard.setStatus(Status.NotAudit.getStatus());
        idCard.setStatusRemark(Status.NotAudit.getStatusRemark());
        idCardDao.save(idCard);
        return idCard;
    }

    @Transactional
    @Override
    public IdCard setIdCardStatus(SetIdCardStatusDTO request) {
        IdCard idCard = idCardDao.getOne(request.getId());
        idCard.setUpdateTime(new Date());
        idCard.setStatus(request.getStatus());
        idCard.setStatusRemark(request.getStatusRemark());
        idCard = idCardDao.save(idCard);

        if(request.getStatus()== Status.AuditSuccess.getStatus()) {
            //审核通过
            //实名认证通过
            //更新用户状态和姓名
            idCard.getUser().setCertified(true);
            idCard.getUser().getDetails().setRealName(idCard.getName());
        }

        return idCard;
    }

    @Override
    public IdCard getIdCardByUserId(Long userId) {
        return idCardDao.findByUserId(userId);
    }


    @Override
    public IdCardVO getIdCardVOByUserId(Long userId) {
        QIdCard e=QIdCard.idCard;
        return queryOne(e,e.user.id.eq(userId),idCardVOConver);
    }

    @Override
    public Page<IdCardVO> getIdCardVOPage(GetIdCardListDTO request, Pageable pageable) {
        QIdCard e=QIdCard.idCard;
        Predicate p=null;
        if(request.getKeyword()!=null){
            p=e.name.like("%"+request.getKeyword()+"%");
        }
        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn()).and(p);
        }
        return queryPage(e,p,idCardVOConver,pageable);
    }

}
