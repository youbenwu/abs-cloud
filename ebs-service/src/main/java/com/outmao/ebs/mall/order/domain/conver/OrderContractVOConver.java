package com.outmao.ebs.mall.order.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.order.entity.QOrderContract;
import com.outmao.ebs.mall.order.vo.OrderContractVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class OrderContractVOConver implements BeanConver<QOrderContract, OrderContractVO> {
    @Override
    public OrderContractVO fromTuple(Tuple t, QOrderContract e) {
        OrderContractVO vo=new OrderContractVO();
        vo.setId(t.get(e.id));
        vo.setUrl(t.get(e.url));
        vo.setName(t.get(e.name));
        return vo;
    }

    @Override
    public Expression<?>[] select(QOrderContract e) {
        return new Expression[]{
                e.id,
                e.order.id,
                e.name,
                e.url
        };
    }
}
