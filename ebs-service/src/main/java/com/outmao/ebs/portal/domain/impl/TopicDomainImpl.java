package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.ArticleDao;
import com.outmao.ebs.portal.dao.TopicArticleDao;
import com.outmao.ebs.portal.dao.TopicDao;
import com.outmao.ebs.portal.domain.ArticleDomain;
import com.outmao.ebs.portal.domain.TopicDomain;
import com.outmao.ebs.portal.domain.conver.TopicArticleVOConver;
import com.outmao.ebs.portal.domain.conver.TopicVOConver;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.QTopic;
import com.outmao.ebs.portal.entity.QTopicArticle;
import com.outmao.ebs.portal.entity.Topic;
import com.outmao.ebs.portal.entity.TopicArticle;
import com.outmao.ebs.portal.vo.ArticleVO;
import com.outmao.ebs.portal.vo.TopicArticleVO;
import com.outmao.ebs.portal.vo.TopicVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopicDomainImpl extends BaseDomain implements TopicDomain {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicArticleDao topicArticleDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleDomain articleDomain;

    @Autowired
    private TopicVOConver topicVOConver;

    @Autowired
    private TopicArticleVOConver topicArticleVOConver;



    @Transactional
    @Override
    public Topic saveTopic(TopicDTO request) {
        Topic topic=request.getId()==null?null:topicDao.getOne(request.getId());
        if(topic==null){
            topic=new Topic();
            topic.setCreateTime(new Date());
            topic.setOrgId(request.getOrgId());
            topic.setUserId(request.getUserId());
        }

        BeanUtils.copyProperties(request,topic,"orgId","userId");
        topic.setUpdateTime(new Date());

        security.hasPermission(topic.getOrgId(),topic.getUserId());

        topicDao.save(topic);

        return topic;
    }

    @Transactional
    @Override
    public void deleteTopicById(Long id) {
        Topic topic=topicDao.getOne(id);

        security.hasPermission(topic.getOrgId(),topic.getUserId());

        topicDao.delete(topic);
    }

    @Override
    public Page<TopicVO> getTopicVOPage(GetTopicListDTO request, Pageable pageable) {

        QTopic e=QTopic.topic;

        Predicate p=null;

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId());
        }

        security.hasPermission(request.getOrgId(),null);

        return queryPage(e,p,topicVOConver,pageable);
    }

    @Transactional
    @Override
    public List<TopicArticle> saveTopicArticleList(TopicArticleListDTO request) {
        Topic topic=topicDao.getOne(request.getTopicId());
        security.hasPermission(topic.getOrgId(),topic.getUserId());
        List<TopicArticle> list=new ArrayList<>(request.getArticles().size());
        request.getArticles().forEach(aid->{
            TopicArticle topicArticle=new TopicArticle();
            topicArticle.setTopic(topic);
            topicArticle.setArticle(articleDao.getOne(aid));
            topicArticle.setCreateTime(new Date());
            list.add(topicArticle);
        });
        topicArticleDao.saveAll(list);
        return list;
    }

    @Transactional
    @Override
    public void deleteTopicArticleList(DeleteTopicArticleListDTO request) {
        Topic topic=topicDao.getOne(request.getTopicId());
        security.hasPermission(topic.getOrgId(),topic.getUserId());
        topicArticleDao.deleteAllByTopicIdAndArticleIdIn(request.getTopicId(),request.getArticles());
    }

    @Override
    public List<TopicArticleVO> getTopicArticleVOList(GetTopicArticleListDTO request) {

        Topic topic=topicDao.getOne(request.getTopicId());

        security.hasPermission(topic.getOrgId(),topic.getUserId());

        QTopicArticle e=QTopicArticle.topicArticle;

        Predicate p=e.topic.id.eq(request.getTopicId());

        List<TopicArticleVO> list=queryList(e,p,topicArticleVOConver);

        List<Long> articleIds =list.stream().map(t->t.getArticleId()).collect(Collectors.toList());

        List<ArticleVO> articles=articleDomain.getArticleVOByIdIn(articleIds);

        Map<Long,ArticleVO> articleMap=articles.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        list.forEach(t->{
            t.setArticle(articleMap.get(t.getArticleId()));
        });

        return list;
    }




}
