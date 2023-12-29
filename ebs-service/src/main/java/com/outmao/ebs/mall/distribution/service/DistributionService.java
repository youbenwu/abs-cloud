package com.outmao.ebs.mall.distribution.service;


import com.outmao.ebs.mall.distribution.service.calculator.CommissionCalculator;
import com.outmao.ebs.mall.order.entity.Order;

public interface DistributionService {


    public void setCommissionCalculator(String key, CommissionCalculator calculator);

    public double calculate(Order order);


}
