package com.outmao.ebs.user.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.user.dao.FeedbackDao;
import com.outmao.ebs.user.domain.FeedbackDomain;
import com.outmao.ebs.user.dto.FeedbackDTO;
import com.outmao.ebs.user.dto.GetFeedbackListDTO;
import com.outmao.ebs.user.dto.SetFeedbackStatusDTO;
import com.outmao.ebs.user.entity.Feedback;
import com.outmao.ebs.user.entity.QFeedback;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class FeedbackDomainImpl extends BaseDomain implements FeedbackDomain {

    @Autowired
    FeedbackDao feedbackDao;

    @Transactional
    @Override
    public Feedback saveFeedback(FeedbackDTO request) {
        Feedback feedback=new Feedback();
        BeanUtils.copyProperties(request,feedback);
        feedback.setCreateTime(new Date());
        feedback.setUpdateTime(new Date());
        feedbackDao.save(feedback);
        return feedback;
    }

    @Transactional
    @Override
    public void deleteFeedbackById(Long id) {
        feedbackDao.deleteById(id);
    }

    @Transactional
    @Override
    public Feedback setFeedbackStatus(SetFeedbackStatusDTO request) {
        Feedback feedback=feedbackDao.getOne(request.getId());
        feedback.setStatus(request.getStatus());
        feedback.setStatusRemark(request.getStatusRemark());
        feedbackDao.save(feedback);
        return feedback;
    }

    @Override
    public Page<Feedback> getFeedbackPage(GetFeedbackListDTO request, Pageable pageable) {
        QFeedback e=QFeedback.feedback;
        Predicate p=null;
        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn());
        }
        return feedbackDao.findAll(p,pageable);
    }


}
