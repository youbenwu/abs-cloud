package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerStatsDomain;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerStatsVO;
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
public class MerchantCustomerStatsDomainImpl extends BaseDomain implements MerchantCustomerStatsDomain {
    @Override
    public MerchantCustomerStatsVO getMerchantCustomerStatsVOByCustomerId(Long customerId) {
        Collection<Long> customerIdIn=new ArrayList<>(1);
        customerIdIn.add(customerId);
        List<MerchantCustomerStatsVO> list=getMerchantCustomerStatsVOListByCustomerIdIn(customerIdIn);
        return list.get(0);
    }

    @Override
    public List<MerchantCustomerStatsVO> getMerchantCustomerStatsVOListByCustomerIdIn(Collection<Long> customerIdIn) {
        List<MerchantCustomerStatsVO> list=new ArrayList<>(customerIdIn.size());
        customerIdIn.forEach(t->{
            MerchantCustomerStatsVO vo=new MerchantCustomerStatsVO();
            vo.setCustomerId(t);
        });
        Map<Long,MerchantCustomerStatsVO> listMap=list.stream().collect(Collectors.toMap(t->t.getCustomerId(), t->t));


        //统计订单数量
        QOrder o= QOrder.order;

        List<Tuple> os=QF.select(o.customerId,o.count(),o.totalAmount.sum()).from(o).groupBy(o.customerId).where(o.customerId.in(customerIdIn)).fetch();

        os.forEach(t->{
            MerchantCustomerStatsVO vo=listMap.get(t.get(o.customerId));
            if(vo!=null) {
                Long c = t.get(o.count());
                Double a = t.get(o.totalAmount.sum());
                vo.setOrderCount(c == null ? 0 : c);
                vo.setOrderAmount(a == null ? 0 : a);
            }
        });

        //统计带看数量
        QTakeLook l=QTakeLook.takeLook;

        List<Tuple> ls=QF.select(l.customerId,l.count()).groupBy(l.customerId).from(l).where(l.customerId.in(customerIdIn)).fetch();

        ls.forEach(t->{
            MerchantCustomerStatsVO vo=listMap.get(t.get(l.customerId));
            if(vo!=null) {
                vo.setLookCount(t.get(l.count()));
            }
        });


        return list;
    }
}
