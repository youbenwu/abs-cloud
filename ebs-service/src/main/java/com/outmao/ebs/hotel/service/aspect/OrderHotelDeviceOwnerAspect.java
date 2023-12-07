package com.outmao.ebs.hotel.service.aspect;


import com.outmao.ebs.hotel.dto.HotelDeviceOwnerBuyDTO;
import com.outmao.ebs.hotel.service.HotelDeviceBuyService;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@Component
public class OrderHotelDeviceOwnerAspect {


    @Autowired
    private HotelDeviceBuyService hotelDeviceBuyService;

    @Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
    public void setOrderStatus() { }


    @Transactional()
    @AfterReturning(returning = "order", pointcut = "setOrderStatus()")
    public void setOrderStatus(JoinPoint jp, Order order){
        if(order==null)
            return;
        if(order.getType()!=null&&order.getType()== ProductType.HOTEL_DEVICE.getType()){
            if(order.getStatus()== OrderStatus.FINISHED.getStatus()){

                //购买了设备
                //添加机主信息
                HotelDeviceOwnerBuyDTO ownerDTO=new HotelDeviceOwnerBuyDTO();
                ownerDTO.setMerchantId(order.getMerchantId());
                ownerDTO.setUserId(order.getUserId());
                ownerDTO.setPartnerId(order.getPartnerId());
                ownerDTO.setQuantity(order.getQuantity());
                ownerDTO.setAmount(order.getTotalAmount());
                ownerDTO.setPrice(order.getTotalAmount()/order.getQuantity());

                hotelDeviceBuyService.saveHotelDeviceOwnerBuy(ownerDTO);

            }

        }

    }






}
