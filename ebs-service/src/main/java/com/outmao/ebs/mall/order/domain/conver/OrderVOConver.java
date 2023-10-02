package com.outmao.ebs.mall.order.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.order.entity.QOrder;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class OrderVOConver implements BeanConver<QOrder, OrderVO> {

    @Override
    public OrderVO fromTuple(Tuple t, QOrder e) {
        OrderVO vo=new OrderVO();

        vo.setId(t.get(e.id));
        vo.setShopId(t.get(e.shop.id));
        vo.setUserId(t.get(e.user.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setMerchantId(t.get(e.merchantId));
        vo.setStoreId(t.get(e.storeId));
        vo.setSellerId(t.get(e.sellerId));
        vo.setBrokerId(t.get(e.brokerId));
        vo.setPartnerId(t.get(e.partnerId));
        vo.setCustomerId(t.get(e.customerId));
        vo.setLookId(t.get(e.lookId));
        vo.setOrderNo(t.get(e.orderNo));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setSuccessTime(t.get(e.successTime));
        vo.setDeliveryTime(t.get(e.deliveryTime));
        vo.setFinishTime(t.get(e.finishTime));
        vo.setCloseTime(t.get(e.closeTime));
        vo.setQuantity(t.get(e.quantity));
        vo.setAmount(t.get(e.amount));
        vo.setRemark(t.get(e.remark));
        vo.setFreight(t.get(e.freight));
        vo.setTotalAmount(t.get(e.totalAmount));
        vo.setPayChannel(t.get(e.payChannel));
        vo.setTradeNo(t.get(e.tradeNo));
        vo.setAddressId(t.get(e.addressId));
        vo.setLogisticsId(t.get(e.logisticsId));

        return vo;
    }

    @Override
    public Expression<?>[] select(QOrder e) {
        return new Expression[]{
                e.id,
                e.shop.id,
                e.user.id,
                e.orgId,
                e.merchantId,
                e.storeId,
                e.sellerId,
                e.brokerId,
                e.partnerId,
                e.customerId,
                e.lookId,
                e.orderNo,
                e.status,
                e.statusRemark,
                e.createTime,
                e.updateTime,
                e.successTime,
                e.deliveryTime,
                e.finishTime,
                e.closeTime,
                e.quantity,
                e.amount,
                e.remark,
                e.freight,
                e.totalAmount,
                e.payChannel,
                e.tradeNo,
                e.addressId,
                e.logisticsId,
        };
    }
}
