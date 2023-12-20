package com.outmao.ebs.hotel.service.aspect;


import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.hotel.common.constant.HotelDeviceLeaseOrderStatus;
import com.outmao.ebs.hotel.dto.CreateHotelDeviceLeaseOrderDTO;
import com.outmao.ebs.hotel.dto.SetHotelDeviceLeaseOrderStatusDTO;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.entity.OrderProductLease;
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
public class OrderHotelDeviceRenterAspect {


    @Autowired
    private HotelDeviceLeaseService hotelDeviceLeaseService;


    @Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
    public void setOrderStatus() { }


    @Transactional()
    @AfterReturning(returning = "order", pointcut = "setOrderStatus()")
    public void setOrderStatus(JoinPoint jp, Order order) {
        if (order == null)
            return;
        if (order.getType() != null && order.getType() == ProductType.HOTEL_DEVICE_LEASE.getType()) {
            if(order.getStatus()== OrderStatus.SUCCESSED.getStatus()){

                //租赁订单完成的时候

                CreateHotelDeviceLeaseOrderDTO leaseOrderDTO=new CreateHotelDeviceLeaseOrderDTO();
                leaseOrderDTO.setOrderNo(order.getOrderNo());
                leaseOrderDTO.setUserId(order.getUserId());
                leaseOrderDTO.setMerchantId(order.getMerchantId());
                leaseOrderDTO.setPartnerId(order.getPartnerId());
                leaseOrderDTO.setPrice(order.getTotalAmount()/order.getQuantity());
                leaseOrderDTO.setAmount(order.getTotalAmount());
                leaseOrderDTO.setQuantity(order.getQuantity());
                OrderProductLease lease=order.getProducts().get(0).getLease();
                if(lease!=null) {
                    leaseOrderDTO.setTime(new TimeSpan(TimeSpan.YEAR,lease.getValue()));
                    leaseOrderDTO.setStartTime(lease.getStartTime());
                    leaseOrderDTO.setEndTime(lease.getEndTime());
                }

                hotelDeviceLeaseService.createHotelDeviceLeaseOrder(leaseOrderDTO);

            }else if(order.getStatus()== OrderStatus.DELIVERED.getStatus()){
                hotelDeviceLeaseService.setHotelDeviceLeaseOrderStatus(
                        new SetHotelDeviceLeaseOrderStatusDTO(
                                order.getOrderNo(),
                                HotelDeviceLeaseOrderStatus.IsSend.getStatus()
                        )
                );

            }else if(order.getStatus()== OrderStatus.CLOSED.getStatus()){
                hotelDeviceLeaseService.setHotelDeviceLeaseOrderStatus(
                        new SetHotelDeviceLeaseOrderStatusDTO(
                                order.getOrderNo(),
                                HotelDeviceLeaseOrderStatus.Closed.getStatus()
                        )
                );

            }
        }

    }




}
