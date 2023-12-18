package com.outmao.ebs.data.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.data.entity.QPhoto;
import com.outmao.ebs.data.vo.PhotoVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class PhotoVOConvert implements BeanConver<QPhoto, PhotoVO> {


    @Override
    public PhotoVO fromTuple(Tuple t, QPhoto e) {
        PhotoVO vo=new PhotoVO();
        vo.setId(t.get(e.id));
        vo.setTarget(t.get(e.target));
        vo.setUserId(t.get(e.userId));
        vo.setMediaId(t.get(e.mediaId));
        vo.setUrl(t.get(e.url));
        vo.setTitle(t.get(e.title));
        vo.setDescription(t.get(e.description));
        vo.setStatus(t.get(e.status));
        vo.setSort(t.get(e.sort));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QPhoto e) {
        return new Expression[]{
                e.id,
                e.target,
                e.userId,
                e.mediaId,
                e.url,
                e.title,
                e.description,
                e.status,
                e.sort,
                e.createTime
        };
    }
}
