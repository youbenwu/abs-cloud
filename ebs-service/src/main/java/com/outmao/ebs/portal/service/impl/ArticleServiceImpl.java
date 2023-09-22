package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.bbs.common.data.GetSubjectItemList;
import com.outmao.ebs.bbs.domain.SubjectDomain;
import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.portal.domain.ArticleCategoryDomain;
import com.outmao.ebs.portal.domain.ArticleDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Article;
import com.outmao.ebs.portal.entity.ArticleCategory;
import com.outmao.ebs.portal.service.ArticleService;
import com.outmao.ebs.portal.vo.ArticleCategoryVO;
import com.outmao.ebs.portal.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {


    @Autowired
    private ArticleDomain articleDomain;

    @Autowired
    private ArticleCategoryDomain articleCategoryDomain;

    @Autowired
    private SubjectDomain subjectDomain;


    @Override
    public ArticleCategory saveArticleCategory(ArticleCategoryDTO request) {
        return articleCategoryDomain.saveArticleCategory(request);
    }

    @Override
    public ArticleCategory setArticleCategoryStatus(SetArticleCategoryStatusDTO request) {
        return articleCategoryDomain.setArticleCategoryStatus(request);
    }

    @Override
    public void deleteArticleCategoryById( Long id) {
        articleCategoryDomain.deleteArticleCategoryById(id);
    }

    @Override
    public List<ArticleCategoryVO> getArticleCategoryVOList(GetArticleCategoryListDTO request) {
        return articleCategoryDomain.getArticleCategoryVOList(request);
    }

    @Override
    public Article saveArticle(ArticleDTO request) {
        return articleDomain.saveArticle(request);
    }

    @Override
    public Article setArticleStatus(SetArticleStatusDTO request) {
        return articleDomain.setArticleStatus(request);
    }

    @Override
    public ArticleVO getArticleVOById(Long id) {
        return articleDomain.getArticleVOById(id);
    }

    @Override
    public Page<ArticleVO> getArticleVOPage(GetArticleListDTO request, Pageable pageable) {
        return articleDomain.getArticleVOPage(request,pageable);
    }

    @Override
    public Page<SubjectBrowseVO<ArticleVO>> getArticleBrowseVOPage(Long userId, Pageable pageable) {


        Page<SubjectBrowseVO<ArticleVO>> page=subjectDomain.getSubjectBrowseVOPage(userId, "Article", new GetSubjectItemList<ArticleVO>() {
            @Override
            public List<ArticleVO> getSubjectItemList(Collection<Long> idIn) {
                return articleDomain.getArticleVOByIdIn(idIn);
            }
        }, pageable);

        return page;
    }

    @Override
    public Page<SubjectCollectionVO<ArticleVO>> getArticleCollectionVOPage(Long userId, Pageable pageable) {

        Page<SubjectCollectionVO<ArticleVO>> page=subjectDomain.getSubjectCollectionVOPage(userId, "Article", new GetSubjectItemList<ArticleVO>() {
            @Override
            public List<ArticleVO> getSubjectItemList(Collection<Long> idIn) {
                return articleDomain.getArticleVOByIdIn(idIn);
            }
        }, pageable);
        return page;
    }





}
