package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QArticleMedia;
import com.outmao.ebs.portal.vo.ArticleMediaVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ArticleMediaVOConver implements BeanConver<QArticleMedia, ArticleMediaVO> {

    @Override
    public ArticleMediaVO fromTuple(Tuple t, QArticleMedia e) {
        ArticleMediaVO vo=new ArticleMediaVO();
        vo.setId(t.get(e.id));
        vo.setArticleId(t.get(e.article.id));
        vo.setMediaId(t.get(e.mediaId));
        vo.setType(t.get(e.type));
        vo.setUrl(t.get(e.url));
        vo.setTitle(t.get(e.title));
        vo.setDescription(t.get(e.description));
        vo.setSort(t.get(e.sort));
        vo.setStatus(t.get(e.status));
        return vo;
    }

    @Override
    public Expression<?>[] select(QArticleMedia e) {
        return new Expression[]{
                e.id,
                e.article.id,
                e.mediaId,
                e.type,
                e.url,
                e.title,
                e.description,
                e.sort,
                e.status,
                e.createTime,
        };
    }
}
