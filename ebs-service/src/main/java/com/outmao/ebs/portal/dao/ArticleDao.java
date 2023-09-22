package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article,Long> {
}
