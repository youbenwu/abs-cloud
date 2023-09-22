package com.outmao.ebs.bbs.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.bbs.common.data.SubjectItem;
import com.outmao.ebs.bbs.entity.QSubjectCollection;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SubjectCollectionVOConver<T extends SubjectItem> implements BeanConver<QSubjectCollection, SubjectCollectionVO<T>> {
    @Override
    public SubjectCollectionVO fromTuple(Tuple t, QSubjectCollection e) {
        SubjectCollectionVO vo=new SubjectCollectionVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setSubjectId(t.get(e.subject.id));
        vo.setMark(t.get(e.mark));
        vo.setRemark(t.get(e.remark));
        vo.setItemId(t.get(e.itemId));
        vo.setItemType(t.get(e.itemType));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QSubjectCollection e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.subject.id,
                e.mark,
                e.remark,
                e.itemId,
                e.itemType,
                e.createTime
        };
    }
}
