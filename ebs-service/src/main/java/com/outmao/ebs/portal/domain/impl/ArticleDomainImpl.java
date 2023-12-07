package com.outmao.ebs.portal.domain.impl;


import com.outmao.ebs.bbs.common.annotation.BindingSubject;
import com.outmao.ebs.bbs.common.annotation.SubjectBrowsesAdd;
import com.outmao.ebs.bbs.common.annotation.SubjectItemFilter;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.portal.dao.ArticleCategoryDao;
import com.outmao.ebs.portal.dao.ArticleDao;
import com.outmao.ebs.portal.dao.ArticleMediaDao;
import com.outmao.ebs.portal.domain.ArticleDomain;
import com.outmao.ebs.portal.domain.conver.ArticleVOConver;
import com.outmao.ebs.portal.domain.conver.ArticleMediaVOConver;
import com.outmao.ebs.portal.domain.conver.SimpleArticleVOConver;
import com.outmao.ebs.portal.dto.ArticleDTO;
import com.outmao.ebs.portal.dto.GetArticleListDTO;
import com.outmao.ebs.portal.dto.SetArticleStatusDTO;
import com.outmao.ebs.portal.entity.*;
import com.outmao.ebs.portal.vo.ArticleMediaVO;
import com.outmao.ebs.portal.vo.ArticleVO;
import com.outmao.ebs.data.common.data.ItemMediaDTO;
import com.outmao.ebs.data.dao.MediaDao;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ArticleDomainImpl extends BaseDomain implements ArticleDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MediaDao mediaDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleMediaDao articleMediaDao;

    @Autowired
    private ArticleCategoryDao articleCategoryDao;




    private ArticleVOConver articleVOConver=new ArticleVOConver();
    private SimpleArticleVOConver simpleArticleVOConver=new SimpleArticleVOConver();
    private ArticleMediaVOConver articleMediaVOConver=new ArticleMediaVOConver();


    @Transactional
    @BindingSubject
    @Override
    public Article saveArticle(ArticleDTO request) {

        Article a=request.getId()==null?null:articleDao.getOne(request.getId());

        if(a==null){
            a=new Article();
            a.setUser(userDao.getOne(request.getUserId()));
            a.setCategory(articleCategoryDao.getOne(request.getCategoryId()));
            a.setOrgId(a.getCategory().getOrgId());
            a.setType(a.getCategory().getType());
            a.setCreateTime(new Date());
        }

        security.hasPermission(a.getOrgId(),a.getUser().getId());

        BeanUtils.copyProperties(request,a,"orgId","medias");

        a.setUpdateTime(new Date());

        //设置未审核
        a.setStatus(Status.NotAudit.getStatus());

        articleDao.save(a);

        if(a.getUrl()==null){
            String url=config.getBaseUrl()+"/article?id="+a.getId();
            a.setUrl(url);
        }

        saveArticleMediaList(a,request.getMedias());

        return a;
    }


    private List<ArticleMedia> saveArticleMediaList(Article a, List<ItemMediaDTO> data){

        if(data==null)
            return new ArrayList<>();

        Map<Long,ItemMediaDTO> dataMap=data.stream().filter(t->t.getId()!=null).collect(Collectors.toMap(t->t.getId(),t->t));


        List<ArticleMedia> list=articleMediaDao.findAllByArticleId(a.getId());

        Map<Long,ArticleMedia> listMap=list.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        List<ArticleMedia> dels=new ArrayList<>();

        list.forEach(t->{
            if(!dataMap.containsKey(t.getId())){
                dels.add(t);
            }
        });

        List<ArticleMedia> saves=new ArrayList<>();


        data.forEach(t->{
            ArticleMedia m=t.getId()==null?null:listMap.get(t.getId());
            if(m==null){
                m=new ArticleMedia();
                m.setArticle(a);
                m.setMediaId(t.getMediaId());
                m.setCreateTime(new Date());
            }
            BeanUtils.copyProperties(t,m,"id");
            saves.add(m);
        });

        if(saves.size()>0)
            articleMediaDao.saveAll(saves);
        if(dels.size()>0)
            articleMediaDao.deleteAll(dels);

        //a.setMedias(saves);


        return saves;
    }

    @Transactional
    @Override
    public Article setArticleStatus(SetArticleStatusDTO request) {
        Article a=articleDao.getOne(request.getId());

        security.hasPermission(a.getOrgId(),a.getUser().getId());

        a.setStatus(request.getStatus());

        articleDao.save(a);

        return a;
    }

    @SubjectBrowsesAdd
    @SubjectItemFilter
    @SetSimpleUser
    @Override
    public ArticleVO getArticleVOById(Long id) {

        QArticle e=QArticle.article;

        ArticleVO vo= queryOne(e,e.id.eq(id),articleVOConver);

        if(vo!=null) {
            vo.setMedias(getArticleMediaVOList(id));
        }

        return vo;
    }

    @SubjectBrowsesAdd
    @SubjectItemFilter
    @SetSimpleUser
    @Override
    public ArticleVO getArticleVOByCode(String code) {
        QArticle e=QArticle.article;

        ArticleVO vo= queryOne(e,e.code.eq(code),articleVOConver);

        if(vo!=null) {
            vo.setMedias(getArticleMediaVOList(vo.getId()));
        }

        return vo;
    }

    @SubjectItemFilter
    @SetSimpleUser
    @Override
    public Page<ArticleVO> getArticleVOPage(GetArticleListDTO request, Pageable pageable) {


        security.hasPermission(request.getOrgId(),request.getUserId());

        QArticle e=QArticle.article;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.title.like("%"+request.getKeyword()+"%").and(p);
        }

        if(request.getCategoryId()!=null){
            Collection<Long> categoryIdIn=new HashSet<>();
            categoryIdIn.add(request.getCategoryId());
            ArticleCategory category=articleCategoryDao.getOne(request.getCategoryId());
            if(!category.isLeaf()){
                category.getChildren().forEach(t->{
                    categoryIdIn.add(t.getId());
                });
            }
            p=e.category.id.in(categoryIdIn).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId()).and(p);
        }

        if(request.getOrgId()!=null) {
            p = e.orgId.eq(request.getOrgId()).and(p);
        }

        Page<ArticleVO> page=queryPage(e,p,simpleArticleVOConver,pageable);

        Map<Long,ArticleVO> map=page.getContent().stream().collect(Collectors.toMap(t->t.getId(),t->t));

        List<ArticleMediaVO> medias=getArticleMediaVOList(page.getContent().stream().map(t->t.getId()).collect(Collectors.toList()));

        medias.forEach(t->{
            ArticleVO vo=map.get(t.getArticleId());
            if(vo.getMedias()==null)
                vo.setMedias(new ArrayList<>());
            vo.getMedias().add(t);
        });

        return page;
    }


    private List<ArticleMediaVO> getArticleMediaVOList(Long articleId){
        QArticleMedia e=QArticleMedia.articleMedia;
        return queryList(e,e.article.id.eq(articleId),articleMediaVOConver);
    }

    private List<ArticleMediaVO> getArticleMediaVOList(Collection<Long> articleIdIn){
        QArticleMedia e=QArticleMedia.articleMedia;
        return queryList(e,e.article.id.in(articleIdIn),articleMediaVOConver);
    }

    @SubjectItemFilter
    @SetSimpleUser
    @Override
    public List<ArticleVO> getArticleVOByIdIn(Collection<Long> idIn) {
        QArticle e=QArticle.article;
        List<ArticleVO> list= queryList(e,e.id.in(idIn),articleVOConver);

        Map<Long,ArticleVO> map=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        List<ArticleMediaVO> medias=getArticleMediaVOList(list.stream().map(t->t.getId()).collect(Collectors.toList()));

        medias.forEach(t->{
            ArticleVO vo=map.get(t.getArticleId());
            if(vo.getMedias()==null)
                vo.setMedias(new ArrayList<>());
            vo.getMedias().add(t);
        });

        return list;

    }



}
