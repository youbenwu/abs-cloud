package com.outmao.ebs.sys.domain.impl;


import com.google.common.collect.Lists;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.sys.dao.FeedbackDao;
import com.outmao.ebs.sys.dao.FeedbackItemDao;
import com.outmao.ebs.sys.domain.FeedbackDomain;
import com.outmao.ebs.sys.dto.*;
import com.outmao.ebs.sys.entity.Feedback;
import com.outmao.ebs.sys.entity.FeedbackItem;
import com.outmao.ebs.sys.entity.QFeedback;
import com.outmao.ebs.sys.entity.QFeedbackItem;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
public class FeedbackDomainImpl extends BaseDomain implements FeedbackDomain {

    @Autowired
    private FeedbackDao feedbackDao;

    @Autowired
    private FeedbackItemDao feedbackItemDao;


    @Transactional
    @Override
    public FeedbackItem saveFeedbackItem(FeedbackItemDTO request) {
        FeedbackItem item=request.getId()==null?null:feedbackItemDao.getOne(request.getId());

        if(item==null){
            item=new FeedbackItem();
            item.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,item);
        item.setUpdateTime(new Date());

        feedbackItemDao.save(item);

        return item;
    }

    @Transactional
    @Override
    public void deleteFeedbackItemById(Long id) {
        feedbackItemDao.deleteById(id);
    }

    @Override
    public List<FeedbackItem> getFeedbackItemList(GetFeedbackItemListDTO request) {

        Predicate p=getPredicate(request);

        List<FeedbackItem> list= Lists.newArrayList(feedbackItemDao.findAll(p));

        return list;
    }

    private Predicate getPredicate(GetFeedbackItemListDTO request){
        QFeedbackItem e=QFeedbackItem.feedbackItem;
        Predicate p=e.id.isNotNull();
        if(request.getType()!=null){
            p=e.type.eq(request.getType());
        }
        return p;
    }

    @Override
    public Page<FeedbackItem> getFeedbackItemPage(GetFeedbackItemListDTO request, Pageable pageable) {

        Predicate p=getPredicate(request);

        return feedbackItemDao.findAll(p,pageable);
    }

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
        Predicate p=e.id.gt(0);
        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }
        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }
        return feedbackDao.findAll(p,pageable);
    }


}
