package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.domain.MerchantStatsDomain;
import com.outmao.ebs.mall.merchant.entity.*;
import com.outmao.ebs.mall.merchant.vo.MerchantStatsVO;
import com.outmao.ebs.mall.order.entity.QOrder;
import com.outmao.ebs.mall.store.entity.QStore;
import com.outmao.ebs.mall.takeLook.entity.QTakeLook;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MerchantStatsDomainImpl extends BaseDomain implements MerchantStatsDomain {



    @Override
    public MerchantStatsVO getMerchantStatsVOByMerchantId(Long merchantId) {
        Collection<Long> merchantIdIn=new ArrayList<>(1);
        merchantIdIn.add(merchantId);
        List<MerchantStatsVO> list=getMerchantStatsVOListByMerchantIdIn(merchantIdIn);
        return list.get(0);
    }

    @Override
    public List<MerchantStatsVO> getMerchantStatsVOListByMerchantIdIn(Collection<Long> merchantIdIn) {
        List<MerchantStatsVO> list=new ArrayList<>(merchantIdIn.size());
        merchantIdIn.forEach(t->{
            MerchantStatsVO vo=new MerchantStatsVO();
            vo.setMerchantId(t);
        });
        Map<Long,MerchantStatsVO> listMap=list.stream().collect(Collectors.toMap(t->t.getMerchantId(), t->t));


        //统计门店数量
        QStore s=QStore.store;

        List<Tuple> ss=QF.select(s.merchant.id,s.count()).from(s).groupBy(s.merchant.id).where(s.merchant.id.in(merchantIdIn)).fetch();

        ss.forEach(t->{
            MerchantStatsVO vo=listMap.get(t.get(s.merchant.id));
            vo.setStoreCount(t.get(s.count()));
        });


        //统计经纪人数量
        QMerchantBroker b= QMerchantBroker.merchantBroker;

        List<Tuple> bs=QF.select(b.merchant.id,b.count()).from(b).groupBy(b.merchant.id).where(b.merchant.id.in(merchantIdIn)).fetch();

        bs.forEach(t->{
            MerchantStatsVO vo=listMap.get(t.get(b.merchant.id));
            vo.setBrokerCount(t.get(b.count()));
        });


        //统计合伙人数量
        QMerchantPartner p=QMerchantPartner.merchantPartner;

        List<Tuple> ps=QF.select(p.merchant.id,p.count()).from(p).groupBy(p.merchant.id).where(p.merchant.id.in(merchantIdIn)).fetch();

        ps.forEach(t->{
            MerchantStatsVO vo=listMap.get(t.get(p.merchant.id));
            vo.setPartnerCount(t.get(p.count()));
        });

        //统计客户数量
        QMerchantCustomer c=QMerchantCustomer.merchantCustomer;

        List<Tuple> cs=QF.select(c.merchant.id,c.count()).groupBy(c.merchant.id).where(c.merchant.id.in(merchantIdIn)).fetch();

        cs.forEach(t->{
            MerchantStatsVO vo=listMap.get(t.get(c.merchant.id));
            vo.setCustomerCount(t.get(c.count()));
        });

        //统计订单数量
        QOrder o= QOrder.order;

        List<Tuple> os=QF.select(o.merchantId,o.count(),o.totalAmount.sum()).groupBy(o.merchantId).where(o.merchantId.in(merchantIdIn)).fetch();

        os.forEach(t->{
            MerchantStatsVO vo=listMap.get(t.get(o.merchantId));
            vo.setOrderCount(t.get(o.count()));
            vo.setOrderAmount(t.get(o.totalAmount.sum()));
        });

        //统计带看数量
        QTakeLook l=QTakeLook.takeLook;

        List<Tuple> ls=QF.select(l.merchantId,l.count()).groupBy(l.merchantId).where(l.merchantId.in(merchantIdIn)).fetch();

        ls.forEach(t->{
            MerchantStatsVO vo=listMap.get(t.get(l.merchantId));
            vo.setLookCount(t.get(l.count()));
        });


        return list;
    }


}
