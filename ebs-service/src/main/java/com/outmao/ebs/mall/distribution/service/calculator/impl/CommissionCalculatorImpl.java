package com.outmao.ebs.mall.distribution.service.calculator.impl;

import com.outmao.ebs.mall.distribution.dao.DistributionConfigDao;
import com.outmao.ebs.mall.distribution.entity.DistributionConfig;
import com.outmao.ebs.mall.distribution.service.calculator.CommissionCalculator;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.entity.OrderProduct;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.product.vo.ProductDistributionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("CommissionCalculatorImpl")
public class CommissionCalculatorImpl implements CommissionCalculator  {


    @Autowired
    private DistributionConfigDao distributionConfigDao;

    @Autowired
    private ProductDao productDao;



    @Override
    public double calculate(Order order) {
        DistributionConfig config=distributionConfigDao.findByMerchantId(order.getMerchantId());
        if(config==null||!config.isEnable())
            return 0;
        List<ProductDistributionVO> ps=productDao.findAllDistributionByIdIn(order.getProducts().stream().map(t->t.getProductId()).collect(Collectors.toList())) ;
        Map<Long,ProductDistributionVO> pmap=ps.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        double totalAmount=0;
        //佣金比率
        double productCommission=config.getProductCommission();

        for (OrderProduct p:order.getProducts()){
            ProductDistributionVO vo=pmap.get(p.getSkuId());
            if(vo==null)
                continue;
            if(!vo.isDistribution())
                continue;

            double rate=productCommission;
            double amount=0;

            if(vo.getCommissionType()==0){
                amount=vo.getCommissionAmount();
            }else{
                rate=vo.getCommissionRate();
            }

            if(amount==0){
                amount=p.getAmount()*rate;
            }

            totalAmount+=amount;
            p.setCommissionAmount(amount);
        }

        return totalAmount;
    }


}
