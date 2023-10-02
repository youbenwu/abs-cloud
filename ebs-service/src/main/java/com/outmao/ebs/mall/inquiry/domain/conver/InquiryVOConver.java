package com.outmao.ebs.mall.inquiry.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.inquiry.entity.QInquiry;
import com.outmao.ebs.mall.inquiry.vo.InquiryVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class InquiryVOConver implements BeanConver<QInquiry, InquiryVO> {
    @Override
    public InquiryVO fromTuple(Tuple t, QInquiry e) {
        InquiryVO vo=new InquiryVO();
        vo.setId(t.get(e.id));
        vo.setMerchantId(t.get(e.merchantId));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setUserId(t.get(e.userId));
        vo.setProductId(t.get(e.productId));
        vo.setProductTitle(t.get(e.productTitle));
        vo.setProductImage(t.get(e.productImage));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setQuantity(t.get(e.quantity));
        vo.setRemark(t.get(e.remark));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QInquiry e) {
        return new Expression[]{
                e.id,
                e.merchantId,
                e.status,
                e.statusRemark,
                e.userId,
                e.productId,
                e.productTitle,
                e.productImage,
                e.name,
                e.phone,
                e.quantity,
                e.remark,
                e.createTime,
                e.updateTime
        };
    }
}
