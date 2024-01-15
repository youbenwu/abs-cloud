package com.outmao.ebs.studio.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.studio.entity.QVideo;
import com.outmao.ebs.studio.vo.VideoVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class VideoVOConver implements BeanConver<QVideo, VideoVO> {
    @Override
    public VideoVO fromTuple(Tuple t, QVideo e) {
        VideoVO vo=new VideoVO();
        vo.setId(t.get(e.id));
        vo.setMovieId(t.get(e.movieId));
        vo.setEpisodeId(t.get(e.episodeId));
        vo.setName(t.get(e.name));
        vo.setUrl(t.get(e.url));
        return vo;
    }

    @Override
    public Expression<?>[] select(QVideo e) {
        return new Expression[]{
                e.id,
                e.movieId,
                e.episodeId,
                e.name,
                e.url,
        };
    }
}
