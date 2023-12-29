package com.outmao.ebs.mall.distribution.service.calculator.impl;


import com.outmao.ebs.hotel.dao.HotelDeviceDao;
import com.outmao.ebs.mall.distribution.entity.QyDistributionConfigLevel;
import com.outmao.ebs.mall.distribution.service.QyDistributionConfigService;
import com.outmao.ebs.mall.distribution.service.calculator.CommissionCalculator;
import com.outmao.ebs.mall.distribution.vo.QyDistributionConfigVO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("QyCommissionCalculator40")
public class QyCommissionCalculator40 implements CommissionCalculator {


    @Autowired
    private QyDistributionConfigService qyDistributionConfigService;
    @Autowired
    private HotelDeviceDao hotelDeviceDao;



    @Override
    public double calculate(Order order) {

        if(order.getType()==null||order.getType()!= ProductType.HOTEL_DEVICE_LEASE.getType())
            return 0;

        QyDistributionConfigVO config=qyDistributionConfigService.getQyDistributionConfigVO();
        if(config==null)
            return 0;

//        long to=hotelDeviceDao.countByLeasePartnerId(order.getPartnerId());
//        long from=to-order.getQuantity();
        long from=hotelDeviceDao.countByLeasePartnerId(order.getPartnerId());
        long to=from+order.getQuantity();


        double totalAmount=0;

        while (from<to){
            QyDistributionConfigLevel level=getLevel(config,from);
            long c=Math.min(level.getDeviceNumberTo()-level.getDeviceNumberFrom(),to-from);
            totalAmount+=level.getPartnerAmount()*c;
            from+=c;
        }

        return totalAmount;
    }


    private QyDistributionConfigLevel getLevel(QyDistributionConfigVO config,long from){
        if(from>=config.getLevelA().getDeviceNumberFrom()&&from<config.getLevelA().getDeviceNumberTo())
            return config.getLevelA();
        if(from>=config.getLevelB().getDeviceNumberFrom()&&from<config.getLevelB().getDeviceNumberTo())
            return config.getLevelB();
        if(from>=config.getLevelC().getDeviceNumberFrom()&&from<config.getLevelC().getDeviceNumberTo())
            return config.getLevelC();
        return config.getLevelD();
    }


}
