package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QArticle;
import com.outmao.ebs.portal.vo.ArticleVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ArticleVOConver implements BeanConver<QArticle, ArticleVO> {


    @Override
    public ArticleVO fromTuple(Tuple t, QArticle e) {
        ArticleVO vo=new ArticleVO();
        vo.setId(t.get(e.id));
        vo.setType(t.get(e.type));
        vo.setSubjectId(t.get(e.subject.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.user.id));
        vo.setCategoryId(t.get(e.category.id));
        vo.setCategoryTitle(t.get(e.category.title));
        vo.setStatus(t.get(e.status));
        vo.setAuthor(t.get(e.author));
        vo.setTitle(t.get(e.title));
        vo.setContent(t.get(e.content));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setUrl(t.get(e.url));
        return vo;
    }

    @Override
    public Expression<?>[] select(QArticle e) {
        return new Expression[]{
                e.id,
                e.type,
                e.subject.id,
                e.orgId,
                e.user.id,
                e.category.id,
                e.category.title,
                e.status,
                e.author,
                e.title,
                e.content,
                e.createTime,
                e.updateTime,
                e.url,
        };
    }
}
