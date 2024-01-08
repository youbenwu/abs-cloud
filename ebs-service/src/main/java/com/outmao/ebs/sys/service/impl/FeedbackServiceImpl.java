package com.outmao.ebs.sys.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.sys.domain.FeedbackDomain;
import com.outmao.ebs.sys.dto.*;
import com.outmao.ebs.sys.entity.Feedback;
import com.outmao.ebs.sys.entity.FeedbackItem;
import com.outmao.ebs.sys.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl extends BaseService implements FeedbackService {


    @Autowired
    private FeedbackDomain feedbackDomain;


    @Override
    public FeedbackItem saveFeedbackItem(FeedbackItemDTO request) {
        return feedbackDomain.saveFeedbackItem(request);
    }

    @Override
    public void deleteFeedbackItemById(Long id) {
        feedbackDomain.deleteFeedbackItemById(id);
    }

    @Override
    public List<FeedbackItem> getFeedbackItemList(GetFeedbackItemListDTO request) {
        return feedbackDomain.getFeedbackItemList(request);
    }

    @Override
    public Page<FeedbackItem> getFeedbackItemPage(GetFeedbackItemListDTO request, Pageable pageable) {
        return feedbackDomain.getFeedbackItemPage(request,pageable);
    }

    @Override
    public Feedback saveFeedback(FeedbackDTO request) {
        return feedbackDomain.saveFeedback(request);
    }


    @Override
    public void deleteFeedbackById(Long id) {
        feedbackDomain.deleteFeedbackById(id);
    }

    @Override
    public Feedback setFeedbackStatus(SetFeedbackStatusDTO request) {
        return feedbackDomain.setFeedbackStatus(request);
    }

    @Override
    public Page<Feedback> getFeedbackPage(GetFeedbackListDTO request, Pageable pageable) {
        return feedbackDomain.getFeedbackPage(request,pageable);
    }



}
