package com.outmao.ebs.bbs.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.bbs.entity.QSubject;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SubjectVOConver implements BeanConver<QSubject, SubjectVO> {


    @Override
    public SubjectVO fromTuple(Tuple t, QSubject e) {
        SubjectVO vo=new SubjectVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setItem(t.get(e.item));
        vo.setTitle(t.get(e.title));
        vo.setType(t.get(e.type));
        vo.setImage(t.get(e.image));
        vo.setContent(t.get(e.content));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setStats(t.get(e.stats));
        return vo;
    }

    @Override
    public Expression<?>[] select(QSubject e) {
        return new Expression<?>[]{
                e.id,
                e.user.id,
                e.item,
                e.title,
                e.image,
                e.type,
                e.content,
                e.createTime,
                e.updateTime,
                e.stats
        };
    }
}
