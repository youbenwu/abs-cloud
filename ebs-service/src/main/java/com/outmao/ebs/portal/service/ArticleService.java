package com.outmao.ebs.portal.service;

import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Article;
import com.outmao.ebs.portal.entity.ArticleCategory;
import com.outmao.ebs.portal.vo.ArticleCategoryVO;
import com.outmao.ebs.portal.vo.ArticleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {


    public ArticleCategory saveArticleCategory(ArticleCategoryDTO request);

    public ArticleCategory setArticleCategoryStatus(SetArticleCategoryStatusDTO request);

    public void deleteArticleCategoryById(Long id);

    public List<ArticleCategoryVO> getArticleCategoryVOList(GetArticleCategoryListDTO request);



    public Article saveArticle(ArticleDTO request);

    public Article setArticleStatus(SetArticleStatusDTO request);

    public ArticleVO getArticleVOById(Long id);

    public Page<ArticleVO> getArticleVOPage(GetArticleListDTO request, Pageable pageable);

    public Page<SubjectBrowseVO<ArticleVO>> getArticleBrowseVOPage(Long userId, Pageable pageable);

    public Page<SubjectCollectionVO<ArticleVO>> getArticleCollectionVOPage(Long userId, Pageable pageable);

}
