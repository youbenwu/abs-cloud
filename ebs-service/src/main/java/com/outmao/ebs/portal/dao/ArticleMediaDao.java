package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.ArticleMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleMediaDao extends JpaRepository<ArticleMedia,Long> {


    public List<ArticleMedia> findAllByArticleId(Long articleId);

}
