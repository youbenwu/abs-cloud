package com.outmao.ebs.mall.order.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.order.entity.QOrderLogisticsStatus;
import com.outmao.ebs.mall.order.vo.OrderLogisticsStatusVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class OrderLogisticsStatusVOConver implements BeanConver<QOrderLogisticsStatus, OrderLogisticsStatusVO> {

    @Override
    public OrderLogisticsStatusVO fromTuple(Tuple t, QOrderLogisticsStatus e) {
        OrderLogisticsStatusVO vo=new OrderLogisticsStatusVO();
        vo.setId(t.get(e.id));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setCreateTime(t.get(e.createTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QOrderLogisticsStatus e) {
        return new Expression[]{
                e.id,
                e.status,
                e.statusRemark,
                e.createTime
        };
    }
}
