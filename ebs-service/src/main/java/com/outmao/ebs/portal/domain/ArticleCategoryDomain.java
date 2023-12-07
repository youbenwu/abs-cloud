package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.ArticleCategoryDTO;
import com.outmao.ebs.portal.dto.GetArticleCategoryListDTO;
import com.outmao.ebs.portal.dto.SetArticleCategoryStatusDTO;
import com.outmao.ebs.portal.entity.ArticleCategory;
import com.outmao.ebs.portal.vo.ArticleCategoryVO;

import java.util.List;

public interface ArticleCategoryDomain {

    public ArticleCategory saveArticleCategory(ArticleCategoryDTO request);

    public ArticleCategory setArticleCategoryStatus(SetArticleCategoryStatusDTO request);

    public void deleteArticleCategoryById(Long id);

    public List<ArticleCategoryVO> getArticleCategoryVOList(GetArticleCategoryListDTO request);


}
