package com.outmao.ebs.mall.distribution.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.distribution.service.DistributionService;
import com.outmao.ebs.mall.distribution.service.calculator.CommissionCalculator;
import com.outmao.ebs.mall.order.entity.Order;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DistributionServiceImpl extends BaseService implements DistributionService , ApplicationListener<ApplicationPreparedEvent> {

    private Map<String, CommissionCalculator> calculators=new HashMap<>();

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        calculators.put("default",(CommissionCalculator)applicationPreparedEvent.getApplicationContext().getBean("CommissionCalculatorImpl"));
        calculators.put("40",(CommissionCalculator)applicationPreparedEvent.getApplicationContext().getBean("QyCommissionCalculator40"));
    }



    @Override
    public void setCommissionCalculator(String key, CommissionCalculator calculator) {
        calculators.put(key,calculator);
    }

    @Override
    public double calculate(Order order) {
        CommissionCalculator calculator=null;
        if(order.getType()!=null){
            calculator=calculators.get(order.getType().toString());
        }
        if(calculator==null){
            calculator=calculators.get("default");
        }
        if(calculator!=null){
            return calculator.calculate(order);
        }
        return 0;
    }


}
