package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.ArticleDTO;
import com.outmao.ebs.portal.dto.GetArticleListDTO;
import com.outmao.ebs.portal.dto.SetArticleStatusDTO;
import com.outmao.ebs.portal.entity.Article;
import com.outmao.ebs.portal.vo.ArticleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface ArticleDomain {


    public Article saveArticle(ArticleDTO request);

    public Article setArticleStatus(SetArticleStatusDTO request);

    public ArticleVO getArticleVOById(Long id);

    public Page<ArticleVO> getArticleVOPage(GetArticleListDTO request, Pageable pageable);

    public List<ArticleVO> getArticleVOByIdIn(Collection<Long> idIn);



}
