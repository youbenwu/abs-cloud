package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProduct;
import com.outmao.ebs.mall.product.vo.ProductVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SimpleProductVOConver implements BeanConver<QProduct,ProductVO> {


    @Override
    public ProductVO fromTuple(Tuple t, QProduct e) {
        ProductVO vo=new ProductVO();
        vo.setId(t.get(e.id));
        vo.setSubjectId(t.get(e.subjectId));
        vo.setType(t.get(e.type));
        vo.setQrCode(t.get(e.qrCode));
        vo.setUrl(t.get(e.url));
        vo.setImage(t.get(e.image));
        vo.setVideo(t.get(e.video));
        vo.setTitle(t.get(e.title));
        vo.setMarks(t.get(e.marks));
        vo.setPrice(t.get(e.price));
        vo.setUnitPrice(t.get(e.unitPrice));
        vo.setMarketPrice(t.get(e.marketPrice));
        vo.setStatus(t.get(e.status));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProduct e) {
        return new Expression[]{
                e.id,
                e.subjectId,
                e.type,
                e.qrCode,
                e.url,
                e.image,
                e.video,
                e.title,
                e.marks,
                e.price,
                e.unitPrice,
                e.marketPrice,
                e.status
        };
    }


}
