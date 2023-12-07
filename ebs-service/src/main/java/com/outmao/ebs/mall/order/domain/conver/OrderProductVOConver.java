package com.outmao.ebs.mall.order.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.order.entity.QOrderProduct;
import com.outmao.ebs.mall.order.vo.OrderProductVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class OrderProductVOConver implements BeanConver<QOrderProduct, OrderProductVO> {

    @Override
    public OrderProductVO fromTuple(Tuple t, QOrderProduct e) {
        OrderProductVO vo=new OrderProductVO();
        vo.setId(t.get(e.id));
        vo.setOrderId(t.get(e.order.id));
        vo.setSnapshotId(t.get(e.snapshotId));
        vo.setProductId(t.get(e.productId));
        vo.setProductTitle(t.get(e.productTitle));
        vo.setProductImage(t.get(e.productImage));
        vo.setSkuId(t.get(e.skuId));
        vo.setSkuName(t.get(e.skuName));
        vo.setPrice(t.get(e.price));
        vo.setQuantity(t.get(e.quantity));
        vo.setAmount(t.get(e.amount));
        vo.setCommissionAmount(t.get(e.commissionAmount));
        vo.setVolume(t.get(e.volume));
        vo.setWeight(t.get(e.weight));
        vo.setRemark(t.get(e.remark));
        vo.setNoDelivery(t.get(e.noDelivery));
        vo.setLease(t.get(e.lease));
        vo.setSellerFinish(t.get(e.sellerFinish));
        return vo;
    }

    @Override
    public Expression<?>[] select(QOrderProduct e) {
        return new Expression[]{
                e.id,
                e.order.id,
                e.snapshotId,
                e.productId,
                e.productTitle,
                e.productImage,
                e.skuId,
                e.skuName,
                e.price,
                e.quantity,
                e.amount,
                e.commissionAmount,
                e.volume,
                e.weight,
                e.remark,
                e.noDelivery,
                e.lease,
                e.sellerFinish,

        };
    }
}
