package com.outmao.ebs.data.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.data.entity.QMedia;
import com.outmao.ebs.data.vo.MediaVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MediaVOConver implements BeanConver<QMedia, MediaVO> {


    @Override
    public MediaVO fromTuple(Tuple t, QMedia e) {
        MediaVO vo=new MediaVO();
        vo.setId(t.get(e.id));
        vo.setContentType(t.get(e.contentType));
        vo.setFormat(t.get(e.format));
        vo.setUrl(t.get(e.url));
        vo.setThumbnailUrl(t.get(e.thumbnailUrl));
        vo.setSize(t.get(e.size));
        vo.setWidth(t.get(e.width));
        vo.setHeight(t.get(e.height));
        vo.setDuration(t.get(e.duration));
        vo.setOrientation(t.get(e.orientation));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMedia e) {
        return new Expression[]{
                e.id,
                e.contentType,
                e.format,
                e.url,
                e.thumbnailUrl,
                e.size,
                e.width,
                e.height,
                e.duration,
                e.orientation
        };
    }
}
