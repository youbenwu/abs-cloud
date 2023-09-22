package com.outmao.ebs.bbs.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.bbs.common.data.SubjectItem;
import com.outmao.ebs.bbs.entity.QSubjectBrowse;
import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SubjectBrowseVOConver<T extends SubjectItem> implements BeanConver<QSubjectBrowse, SubjectBrowseVO<T>> {

    @Override
    public SubjectBrowseVO fromTuple(Tuple t, QSubjectBrowse e) {

        SubjectBrowseVO vo=new SubjectBrowseVO();

        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.userId));
        vo.setSubjectId(t.get(e.subjectId));
        vo.setItemId(t.get(e.itemId));
        vo.setItemType(t.get(e.itemType));
        vo.setCreateTime(t.get(e.createTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QSubjectBrowse e) {
        return new Expression[]{
                e.id,
                e.userId,
                e.subjectId,
                e.itemId,
                e.itemType,
                e.createTime,
        };
    }
}
