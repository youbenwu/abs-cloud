package com.outmao.ebs.user.service;

import com.outmao.ebs.user.dto.FeedbackDTO;
import com.outmao.ebs.user.dto.GetFeedbackListDTO;
import com.outmao.ebs.user.dto.SetFeedbackStatusDTO;
import com.outmao.ebs.user.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    public Feedback saveFeedback(FeedbackDTO request);

    public void deleteFeedbackById(Long id);

    public Feedback setFeedbackStatus(SetFeedbackStatusDTO request);

    public Page<Feedback> getFeedbackPage(GetFeedbackListDTO request, Pageable pageable);

}
