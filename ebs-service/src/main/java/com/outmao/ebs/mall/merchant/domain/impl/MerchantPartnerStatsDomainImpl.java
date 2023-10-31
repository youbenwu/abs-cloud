package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.domain.MerchantPartnerStatsDomain;
import com.outmao.ebs.mall.merchant.entity.QMerchantCustomer;
import com.outmao.ebs.mall.merchant.vo.MerchantPartnerStatsVO;
import com.outmao.ebs.mall.order.entity.QOrder;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MerchantPartnerStatsDomainImpl extends BaseDomain implements MerchantPartnerStatsDomain {
    @Override
    public MerchantPartnerStatsVO getMerchantPartnerStatsVOByPartnerId(Long partnerId) {
        Collection<Long> partnerIdIn=new ArrayList<>(1);
        partnerIdIn.add(partnerId);
        List<MerchantPartnerStatsVO> list=getMerchantPartnerStatsVOByPartnerIdIn(partnerIdIn);
        return list.get(0);
    }

    @Override
    public List<MerchantPartnerStatsVO> getMerchantPartnerStatsVOByPartnerIdIn(Collection<Long> partnerIdIn) {
        List<MerchantPartnerStatsVO> list=new ArrayList<>(partnerIdIn.size());
        partnerIdIn.forEach(t->{
            MerchantPartnerStatsVO vo=new MerchantPartnerStatsVO();
            vo.setPartnerId(t);
        });
        Map<Long,MerchantPartnerStatsVO> listMap=list.stream().collect(Collectors.toMap(t->t.getPartnerId(), t->t));

        //统计一级客户数量
        QMerchantCustomer c=QMerchantCustomer.merchantCustomer;

        List<Tuple> cs=QF.select(c.partner.id,c.count()).groupBy(c.partner.id).from(c).where(c.broker.id.in(partnerIdIn)).fetch();

        cs.forEach(t->{
            MerchantPartnerStatsVO vo=listMap.get(t.get(c.partner.id));
            vo.setCustomerCount(t.get(c.count()));
        });

        //统计二级客户数量
        cs=QF.select(c.partner.parent.id,c.count()).groupBy(c.partner.parent.id).from(c).where(c.partner.parent.id.in(partnerIdIn)).fetch();

        cs.forEach(t->{
            MerchantPartnerStatsVO vo=listMap.get(t.get(c.partner.parent.id));
            vo.setChildrenCustomerCount(t.get(c.count()));
        });


        //统计一级订单数量
        QOrder o= QOrder.order;

        List<Tuple> os=QF.select(o.partnerId,o.count(),o.totalAmount.sum()).groupBy(o.partnerId).from(o).where(o.partnerId.in(partnerIdIn)).fetch();

        os.forEach(t->{
            MerchantPartnerStatsVO vo=listMap.get(t.get(o.partnerId));
            if(vo!=null) {
                vo.setOrderCount(t.get(o.count()));
                vo.setOrderAmount(t.get(o.totalAmount.sum()));
            }
        });

        //统计二级订单数量
        os=QF.select(o.partnerParentId,o.count(),o.totalAmount.sum()).groupBy(o.partnerParentId).from(o).where(o.partnerParentId.in(partnerIdIn)).fetch();

        os.forEach(t->{
            MerchantPartnerStatsVO vo=listMap.get(t.get(o.partnerParentId));
            if(vo!=null) {
                vo.setChildrenOrderCount(t.get(o.count()));
                vo.setChildrenOrderAmount(t.get(o.totalAmount.sum()));
            }
        });


        return list;
    }
}
