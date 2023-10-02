package com.outmao.ebs.mall.store.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.order.entity.QOrder;
import com.outmao.ebs.mall.store.domain.StoreStatsDomain;
import com.outmao.ebs.mall.store.entity.QStoreMember;
import com.outmao.ebs.mall.store.vo.StoreStatsVO;
import com.outmao.ebs.mall.takeLook.entity.QTakeLook;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class StoreStatsDomainImpl extends BaseDomain implements StoreStatsDomain {


    @Override
    public StoreStatsVO getStoreStatsVOByStoreId(Long storeId) {
        Collection<Long> storeIdIn=new ArrayList<>(1);
        storeIdIn.add(storeId);
        List<StoreStatsVO> list= getStoreStatsVOListByStoreIdIn(storeIdIn);
        return list.get(0);
    }

    @Override
    public List<StoreStatsVO> getStoreStatsVOListByStoreIdIn(Collection<Long> storeIdIn) {
        List<StoreStatsVO> list=new ArrayList<>(storeIdIn.size());
        storeIdIn.forEach(t->{
            StoreStatsVO vo=new StoreStatsVO();
            vo.setStoreId(t);
        });
        Map<Long,StoreStatsVO> listMap=list.stream().collect(Collectors.toMap(t->t.getStoreId(), t->t));

        QStoreMember m=QStoreMember.storeMember;

        List<Tuple> ms=QF.select(m.store.id,m.count()).groupBy(m.store.id).where(m.store.id.in(storeIdIn)).fetch();

        ms.forEach(t->{
            StoreStatsVO vo=listMap.get(t.get(m.store.id));
            vo.setMemberCount(t.get(m.count()));
        });

        //统计订单数量
        QOrder o= QOrder.order;

        List<Tuple> os=QF.select(o.storeId,o.count(),o.totalAmount.sum()).groupBy(o.storeId).where(o.storeId.in(storeIdIn)).fetch();

        os.forEach(t->{
            StoreStatsVO vo=listMap.get(t.get(o.storeId));
            vo.setOrderCount(t.get(o.count()));
            vo.setOrderAmount(t.get(o.totalAmount.sum()));
        });

        //统计带看数量
        QTakeLook l=QTakeLook.takeLook;

        List<Tuple> ls=QF.select(l.storeId,l.count()).groupBy(l.storeId).where(l.storeId.in(storeIdIn)).fetch();

        ls.forEach(t->{
            StoreStatsVO vo=listMap.get(t.get(l.storeId));
            vo.setLookCount(t.get(l.count()));
        });

        return list;
    }


}
