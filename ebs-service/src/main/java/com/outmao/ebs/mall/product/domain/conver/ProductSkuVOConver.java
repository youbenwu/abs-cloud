package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductSku;
import com.outmao.ebs.mall.product.vo.ProductSkuVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductSkuVOConver implements BeanConver<QProductSku, ProductSkuVO> {


    @Override
    public ProductSkuVO fromTuple(Tuple t, QProductSku e) {
        ProductSkuVO vo=new ProductSkuVO();
        vo.setId(t.get(e.id));
        vo.setProductId(t.get(e.product.id));
        vo.setSkuNo(t.get(e.skuNo));
        vo.setName(t.get(e.name));
        vo.setMarks(t.get(e.marks));
        vo.setKey(t.get(e.key));
        vo.setValue(t.get(e.value));
        vo.setImage(t.get(e.image));
        //vo.setImages(t.getSubStatus(e.images));
        vo.setPrice(t.get(e.price));
        vo.setUnitPrice(t.get(e.unitPrice));
        vo.setStock(t.get(e.stock));
        vo.setAlarmStock(t.get(e.alarmStock));
        vo.setLease(t.get(e.lease));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductSku e) {
        return new Expression[]{
                e.id,
                e.product.id,
                e.skuNo,
                e.name,
                e.marks,
                e.key,
                e.value,
                e.image,
                //e.images,
                e.price,
                e.unitPrice,
                e.stock,
                e.alarmStock,
                e.lease,
        };
    }
}
