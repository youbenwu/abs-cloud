package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.FeedbackDomain;
import com.outmao.ebs.user.dto.FeedbackDTO;
import com.outmao.ebs.user.dto.GetFeedbackListDTO;
import com.outmao.ebs.user.dto.SetFeedbackStatusDTO;
import com.outmao.ebs.user.entity.Feedback;
import com.outmao.ebs.user.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends BaseService implements FeedbackService {


    @Autowired
    private FeedbackDomain feedbackDomain;


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
