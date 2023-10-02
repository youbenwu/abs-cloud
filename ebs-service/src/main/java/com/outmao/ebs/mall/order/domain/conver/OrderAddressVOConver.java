package com.outmao.ebs.mall.order.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.order.entity.QOrderAddress;
import com.outmao.ebs.mall.order.vo.OrderAddressVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class OrderAddressVOConver implements BeanConver<QOrderAddress, OrderAddressVO> {

    @Override
    public OrderAddressVO fromTuple(Tuple t, QOrderAddress e) {

        OrderAddressVO vo=new OrderAddressVO();

        vo.setId(t.get(e.id));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setPhone2(t.get(e.phone2));
        vo.setProvince(t.get(e.province));
        vo.setCity(t.get(e.city));
        vo.setDistrict(t.get(e.district));
        vo.setStreet(t.get(e.street));
        vo.setDetails(t.get(e.details));
        vo.setFullAddress(t.get(e.fullAddress));
        vo.setPostalCode(t.get(e.postalCode));

        return vo;
    }

    @Override
    public Expression<?>[] select(QOrderAddress e) {
        return new Expression[]{
                e.id,
                e.name,
                e.phone,
                e.phone2,
                e.latitude,
                e.longitude,
                e.province,
                e.city,
                e.district,
                e.street,
                e.details,
                e.fullAddress,
                e.postalCode
        };
    }
}
