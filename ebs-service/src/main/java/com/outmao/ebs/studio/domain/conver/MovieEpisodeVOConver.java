package com.outmao.ebs.studio.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.studio.entity.QMovieEpisode;
import com.outmao.ebs.studio.vo.MovieEpisodeVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MovieEpisodeVOConver implements BeanConver<QMovieEpisode, MovieEpisodeVO> {
    @Override
    public MovieEpisodeVO fromTuple(Tuple t, QMovieEpisode e) {
        MovieEpisodeVO vo=new MovieEpisodeVO();
        vo.setId(t.get(e.id));
        vo.setSubjectId(t.get(e.subjectId));
        vo.setMovieId(t.get(e.movieId));
        vo.setUserId(t.get(e.userId));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        vo.setIndex(t.get(e.index));
        vo.setCover(t.get(e.cover));
        vo.setFeeType(t.get(e.feeType));
        vo.setPrice(t.get(e.price));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMovieEpisode e) {
        return new Expression[]{
                e.id,
                e.subjectId,
                e.movieId,
                e.userId,
                e.name,
                e.intro,
                e.index,
                e.cover,
                e.feeType,
                e.price,
                e.createTime,
                e.updateTime,
        };
    }
}
