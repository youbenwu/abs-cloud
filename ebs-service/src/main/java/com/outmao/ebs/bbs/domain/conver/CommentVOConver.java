package com.outmao.ebs.bbs.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.bbs.entity.QComment;
import com.outmao.ebs.bbs.vo.CommentVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class CommentVOConver implements BeanConver<QComment, CommentVO> {


    @Override
    public CommentVO fromTuple(Tuple t, QComment e) {
        CommentVO vo=new CommentVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setPostId(t.get(e.post.id));
        vo.setToId(t.get(e.to.id));
        vo.setStatus(t.get(e.status));
        vo.setContent(t.get(e.content));
        vo.setImages(t.get(e.images));
        vo.setVideo(t.get(e.video));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setStats(t.get(e.stats));
        return vo;
    }

    @Override
    public Expression<?>[] select(QComment e) {
        return new Expression<?>[]
                {
                        e.id,
                        e.user.id,
                        e.post.id,
                        e.to.id,
                        e.status,
                        e.content,
                        e.images,
                        e.video,
                        e.createTime,
                        e.updateTime,
                        e.stats
                };
    }
}
