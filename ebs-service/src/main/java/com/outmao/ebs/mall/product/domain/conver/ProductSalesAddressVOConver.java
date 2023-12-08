package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProductSalesAddress;
import com.outmao.ebs.mall.product.vo.ProductAddressVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductSalesAddressVOConver implements BeanConver<QProductSalesAddress, ProductAddressVO> {


    @Override
    public ProductAddressVO fromTuple(Tuple t, QProductSalesAddress e) {
        ProductAddressVO vo=new ProductAddressVO();

        vo.setId(t.get(e.id));
        vo.setProductId(t.get(e.productId));
        vo.setLatitude(t.get(e.latitude));
        vo.setLongitude(t.get(e.longitude));
        vo.setProvince(t.get(e.province));
        vo.setCity(t.get(e.city));
        vo.setDistrict(t.get(e.district));
        vo.setStreet(t.get(e.street));
        vo.setDetails(t.get(e.details));
        vo.setFullAddress(t.get(e.fullAddress));
        vo.setZipCode(t.get(e.zipCode));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProductSalesAddress e) {
        return new Expression[]{
                e.id,
                e.productId,
                e.latitude,
                e.longitude,
                e.province,
                e.city,
                e.district,
                e.street,
                e.district,
                e.details,
                e.fullAddress,
                e.zipCode,
                e.createTime,
                e.updateTime,
        };
    }


}
