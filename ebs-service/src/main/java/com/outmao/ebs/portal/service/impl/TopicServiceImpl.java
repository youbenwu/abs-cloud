package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Topic;
import com.outmao.ebs.portal.entity.TopicArticle;
import com.outmao.ebs.portal.service.TopicService;
import com.outmao.ebs.portal.vo.TopicArticleVO;
import com.outmao.ebs.portal.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicServiceImpl extends BaseService implements TopicService {

    @Autowired
    private TopicService topicService;

    @Override
    public Topic saveTopic(TopicDTO request) {
        return topicService.saveTopic(request);
    }

    @Override
    public void deleteTopicById(Long id) {
        topicService.deleteTopicById(id);
    }

    @Override
    public Page<TopicVO> getTopicVOPage(GetTopicListDTO request, Pageable pageable) {
        return topicService.getTopicVOPage(request,pageable);
    }

    @Override
    public List<TopicArticle> saveTopicArticleList(TopicArticleListDTO request) {
        return topicService.saveTopicArticleList(request);
    }

    @Override
    public void deleteTopicArticleList(DeleteTopicArticleListDTO request) {
        topicService.deleteTopicArticleList(request);
    }

    @Override
    public List<TopicArticleVO> getTopicArticleVOList(GetTopicArticleListDTO request) {
        return topicService.getTopicArticleVOList(request);
    }
}
