package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductImage;
import com.outmao.ebs.mall.product.vo.ProductImageVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductImageVOConver implements BeanConver<QProductImage, ProductImageVO> {
    @Override
    public ProductImageVO fromTuple(Tuple t, QProductImage e) {
        ProductImageVO vo=new ProductImageVO();
        vo.setId(t.get(e.id));
        vo.setMediaId(t.get(e.mediaId));
        vo.setUrl(t.get(e.url));
        vo.setType(t.get(e.type));
        vo.setSkuKey(t.get(e.skuKey));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductImage e) {
        return new Expression[]{
                e.id,
                e.mediaId,
                e.url,
                e.type,
                e.skuKey
        };
    }
}
