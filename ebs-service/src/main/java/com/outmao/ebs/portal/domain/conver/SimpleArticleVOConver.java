package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QArticle;
import com.outmao.ebs.portal.vo.ArticleVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleArticleVOConver implements BeanConver<QArticle, ArticleVO> {


    @Override
    public ArticleVO fromTuple(Tuple t, QArticle e) {

        ArticleVO vo=new ArticleVO();
        vo.setId(t.get(e.id));
        vo.setSubjectId(t.get(e.subject.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.user.id));
        vo.setCategoryId(t.get(e.category.id));
        vo.setCategoryTitle(t.get(e.category.title));
        vo.setStatus(t.get(e.status));
        vo.setAuthor(t.get(e.author));
        vo.setTitle(t.get(e.title));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setUrl(t.get(e.url));
        vo.setCode(t.get(e.code));
        return vo;
    }

    @Override
    public Expression<?>[] select(QArticle e) {
        return new Expression[]{
                e.id,
                e.subject.id,
                e.orgId,
                e.user.id,
                e.category.id,
                e.category.title,
                e.status,
                e.author,
                e.title,
                e.updateTime,
                e.url,
                e.code,
        };
    }
}
