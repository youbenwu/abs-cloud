package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductMedia;
import com.outmao.ebs.mall.product.vo.ProductMediaVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductMediaVOConver implements BeanConver<QProductMedia, ProductMediaVO> {

    @Override
    public ProductMediaVO fromTuple(Tuple t, QProductMedia e) {
        ProductMediaVO vo=new ProductMediaVO();
        vo.setId(t.get(e.id));
        vo.setProductId(t.get(e.product.id));
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
    public Expression<?>[] select(QProductMedia e) {
        return new Expression[]{
                e.id,
                e.product.id,
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
