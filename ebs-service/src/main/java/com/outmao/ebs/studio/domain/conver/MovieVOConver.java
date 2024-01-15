package com.outmao.ebs.studio.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.studio.entity.QMovie;
import com.outmao.ebs.studio.vo.MovieVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MovieVOConver  implements BeanConver<QMovie, MovieVO> {

    @Override
    public MovieVO fromTuple(Tuple t, QMovie e) {
        MovieVO vo=new MovieVO();
        vo.setId(t.get(e.id));
        vo.setSubjectId(t.get(e.subjectId));
        vo.setOrgId(t.get(e.orgId));
        vo.setUserId(t.get(e.userId));
        vo.setCategoryId(t.get(e.categoryId));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        vo.setCover(t.get(e.cover));
        vo.setFeeType(t.get(e.feeType));
        vo.setPrice(t.get(e.price));
        vo.setProductId(t.get(e.productId));
        vo.setReleaseTime(t.get(e.releaseTime));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMovie e) {
        return new Expression[]{
                e.id,
                e.subjectId,
                e.orgId,
                e.userId,
                e.categoryId,
                e.name,
                e.intro,
                e.cover,
                e.feeType,
                e.price,
                e.productId,
                e.releaseTime,
                e.createTime,
                e.updateTime,
        };
    }
}
