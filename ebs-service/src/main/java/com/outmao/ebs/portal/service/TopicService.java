package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Topic;
import com.outmao.ebs.portal.entity.TopicArticle;
import com.outmao.ebs.portal.vo.TopicArticleVO;
import com.outmao.ebs.portal.vo.TopicVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicService {

    public Topic saveTopic(TopicDTO request);

    public void deleteTopicById(Long id);

    public Page<TopicVO> getTopicVOPage(GetTopicListDTO request, Pageable pageable);

    public List<TopicArticle> saveTopicArticleList(TopicArticleListDTO request);

    public void deleteTopicArticleList(DeleteTopicArticleListDTO request);

    public List<TopicArticleVO>  getTopicArticleVOList(GetTopicArticleListDTO request);


}
