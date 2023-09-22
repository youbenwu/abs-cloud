package com.outmao.ebs.bbs.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.bbs.entity.QPost;
import com.outmao.ebs.bbs.vo.PostVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class PostVOConver implements BeanConver<QPost, PostVO> {

    @Override
    public PostVO fromTuple(Tuple t, QPost e) {
        PostVO vo=new PostVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setSubjectId(t.get(e.subject.id));
        vo.setItem(t.get(e.item));
        vo.setTitle(t.get(e.title));
        vo.setContent(t.get(e.content));
        vo.setImages(t.get(e.images));
        vo.setVideo(t.get(e.video));
        vo.setMark(t.get(e.mark));
        vo.setTop(t.get(e.top));
        vo.setStatus(t.get(e.status));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setStats(t.get(e.stats));
        return vo;
    }

    @Override
    public Expression<?>[] select(QPost e) {
        return new Expression<?>[]{
                e.id,
                e.user.id,
                e.subject.id,
                e.item,
                e.title,
                e.content,
                e.images,
                e.video,
                e.mark,
                e.stats,
                e.top,
                e.status,
                e.createTime,
                e.updateTime,
                e.stats,
        };
    }

}
