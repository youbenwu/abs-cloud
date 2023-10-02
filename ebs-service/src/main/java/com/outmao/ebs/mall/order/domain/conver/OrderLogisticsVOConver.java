package com.outmao.ebs.mall.order.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.order.entity.QOrderLogistics;
import com.outmao.ebs.mall.order.vo.OrderLogisticsVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class OrderLogisticsVOConver implements BeanConver<QOrderLogistics, OrderLogisticsVO> {


    @Override
    public OrderLogisticsVO fromTuple(Tuple t, QOrderLogistics e) {
        OrderLogisticsVO vo=new OrderLogisticsVO();
        vo.setId(t.get(e.id));
        vo.setOrderId(t.get(e.orderId));
        vo.setLogisticsInfo(t.get(e.logisticsInfo));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setStatusContent(t.get(e.statusContent));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QOrderLogistics e) {
        return new Expression[]{
                e.id,
                e.orderId,
                e.logisticsInfo,
                e.status,
                e.statusRemark,
                e.statusContent,
                e.createTime,
                e.updateTime
        };
    }

}
