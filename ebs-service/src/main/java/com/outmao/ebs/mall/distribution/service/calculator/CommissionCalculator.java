package com.outmao.ebs.mall.distribution.service.calculator;

import com.outmao.ebs.mall.order.entity.Order;

public interface CommissionCalculator {

    public double calculate(Order order);

}
