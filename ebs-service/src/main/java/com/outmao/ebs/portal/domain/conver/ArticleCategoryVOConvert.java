package com.outmao.ebs.portal.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.portal.entity.QArticleCategory;
import com.outmao.ebs.portal.vo.ArticleCategoryVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ArticleCategoryVOConvert implements BeanConver<QArticleCategory, ArticleCategoryVO> {


    @Override
    public ArticleCategoryVO fromTuple(Tuple t, QArticleCategory e) {

        ArticleCategoryVO vo=new ArticleCategoryVO();
        vo.setOrgId(t.get(e.orgId));
        vo.setId(t.get(e.id));
        vo.setType(t.get(e.type));
        vo.setParentId(t.get(e.parent.id));
        vo.setLevel(t.get(e.level));
        vo.setLeaf(t.get(e.leaf));
        vo.setSort(t.get(e.sort));
        vo.setStatus(t.get(e.status));
        vo.setImage(t.get(e.image));
        vo.setTitle(t.get(e.title));
        vo.setLetter(t.get(e.letter));
        vo.setDescription(t.get(e.description));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QArticleCategory e) {
        return new Expression[]{
                e.orgId,
                e.id,
                e.type,
                e.parent.id,
                e.level,
                e.leaf,
                e.sort,
                e.status,
                e.image,
                e.title,
                e.letter,
                e.description,
                e.createTime,
                e.updateTime
        };
    }
}
