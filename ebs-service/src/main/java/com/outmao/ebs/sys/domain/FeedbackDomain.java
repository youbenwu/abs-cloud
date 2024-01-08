package com.outmao.ebs.sys.domain;

import com.outmao.ebs.sys.dto.*;
import com.outmao.ebs.sys.entity.Feedback;
import com.outmao.ebs.sys.entity.FeedbackItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedbackDomain {


    public FeedbackItem saveFeedbackItem(FeedbackItemDTO request);

    public void deleteFeedbackItemById(Long id);

    public List<FeedbackItem> getFeedbackItemList(GetFeedbackItemListDTO request);

    public Page<FeedbackItem> getFeedbackItemPage(GetFeedbackItemListDTO request,Pageable pageable);


    public Feedback saveFeedback(FeedbackDTO request);

    public void deleteFeedbackById(Long id);

    public Feedback setFeedbackStatus(SetFeedbackStatusDTO request);

    public Page<Feedback> getFeedbackPage(GetFeedbackListDTO request, Pageable pageable);


}
