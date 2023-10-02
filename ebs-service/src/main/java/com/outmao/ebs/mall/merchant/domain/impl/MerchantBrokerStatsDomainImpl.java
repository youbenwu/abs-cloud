package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.domain.MerchantBrokerStatsDomain;
import com.outmao.ebs.mall.merchant.entity.QMerchantCustomer;
import com.outmao.ebs.mall.merchant.entity.QMerchantPartner;
import com.outmao.ebs.mall.merchant.vo.MerchantBrokerStatsVO;
import com.outmao.ebs.mall.order.entity.QOrder;
import com.outmao.ebs.mall.takeLook.entity.QTakeLook;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class MerchantBrokerStatsDomainImpl extends BaseDomain implements MerchantBrokerStatsDomain {



    @Override
    public MerchantBrokerStatsVO getMerchantBrokerStatsVOBybrokerId(Long brokerId) {
        Collection<Long> brokerIdIn=new ArrayList<>(1);
        brokerIdIn.add(brokerId);
        List<MerchantBrokerStatsVO> list=getMerchantBrokerStatsVOListByBrokerIdIn(brokerIdIn);
        return list.get(0);
    }

    @Override
    public List<MerchantBrokerStatsVO> getMerchantBrokerStatsVOListByBrokerIdIn(Collection<Long> brokerIdIn) {

        List<MerchantBrokerStatsVO> list=new ArrayList<>(brokerIdIn.size());
        brokerIdIn.forEach(t->{
            MerchantBrokerStatsVO vo=new MerchantBrokerStatsVO();
            vo.setBrokerId(t);
        });
        Map<Long,MerchantBrokerStatsVO> listMap=list.stream().collect(Collectors.toMap(t->t.getBrokerId(),t->t));

        //统计合伙人数量
        QMerchantPartner p=QMerchantPartner.merchantPartner;

        List<Tuple> ps=QF.select(p.broker.id,p.count()).from(p).groupBy(p.broker.id).where(p.broker.id.in(brokerIdIn)).fetch();

        ps.forEach(t->{
            MerchantBrokerStatsVO vo=listMap.get(t.get(p.broker.id));
            vo.setPartnerCount(t.get(p.count()));
        });

        //统计客户数量
        QMerchantCustomer c=QMerchantCustomer.merchantCustomer;

        List<Tuple> cs=QF.select(c.broker.id,c.count()).groupBy(c.broker.id).where(c.broker.id.in(brokerIdIn)).fetch();

        cs.forEach(t->{
            MerchantBrokerStatsVO vo=listMap.get(t.get(c.broker.id));
            vo.setCustomerCount(t.get(c.count()));
        });

        //统计订单数量
        QOrder o= QOrder.order;

        List<Tuple> os=QF.select(o.brokerId,o.count(),o.totalAmount.sum()).groupBy(o.brokerId).where(o.brokerId.in(brokerIdIn)).fetch();

        os.forEach(t->{
            MerchantBrokerStatsVO vo=listMap.get(t.get(o.brokerId));
            vo.setOrderCount(t.get(o.count()));
            vo.setOrderAmount(t.get(o.totalAmount.sum()));
        });

        //统计带看数量
        QTakeLook l=QTakeLook.takeLook;

        List<Tuple> ls=QF.select(l.brokerId,l.count()).groupBy(l.brokerId).where(l.brokerId.in(brokerIdIn)).fetch();

        ls.forEach(t->{
            MerchantBrokerStatsVO vo=listMap.get(t.get(l.brokerId));
            vo.setLookCount(t.get(l.count()));
        });


        return list;
    }





}
