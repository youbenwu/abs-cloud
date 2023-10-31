package com.outmao.ebs.hotel.service.aspect;


import com.outmao.ebs.hotel.dto.HotelDeviceOwnerDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.mall.merchant.dao.MerchantBrokerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.dao.MerchantPartnerDao;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.dto.UserCommissionRecordDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
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
    private MerchantPartnerDao merchantPartnerDao;

    @Autowired
    private UserCommissionDomain userCommissionDomain;

    @Autowired
    private OrderService orderService;

    @Autowired
    private HotelService hotelService;

    @Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
    public void setOrderStatus() { }


    @Transactional()
    @AfterReturning(returning = "order", pointcut = "setOrderStatus()")
    public void setOrderStatus(JoinPoint jp, Order order){
        if(order==null)
            return;
        if(order.getType()!=null&&order.getType()== ProductType.HOTEL_DEVICE.getType()){
            if(order.getStatus()== OrderStatus.SUCCESSED.getStatus()){
                 //购买了设备，支付成功
                //添加机主信息
                HotelDeviceOwner owner=hotelService.getHotelDeviceOwnerByUserId(order.getUserId());
                HotelDeviceOwnerDTO ownerDTO=new HotelDeviceOwnerDTO();
                ownerDTO.setUserId(order.getUserId());
                ownerDTO.setPartnerId(order.getPartnerId());
                ownerDTO.setQuantity(order.getQuantity());
                ownerDTO.setAmount(order.getTotalAmount());
                ownerDTO.setPrice(order.getProducts().get(0).getPrice());
                if(owner!=null){
                    ownerDTO.setName(owner.getName());
                    ownerDTO.setPhone(owner.getPhone());
                }
                hotelService.saveHotelDeviceOwner(ownerDTO);

                //计算合伙人佣金

                if(order.getPartnerId()!=null){
                    long total=hotelService.getHotelDeviceCountByPartnerId(order.getPartnerId());
                    long adds=order.getQuantity();

                    System.out.println("total:"+total+" adds:"+adds);

                    double amount=800*adds;
                    if(total>50){
                        //大于50的部分 +200佣金
                        amount+=200*Math.min(adds,total-50);
                    }
                    if(total>100){
                        //大于100的部分 +200佣金
                        amount+=200*Math.min(adds,total-100);
                    }

                    MerchantPartner partner=merchantPartnerDao.getOne(order.getPartnerId());
                    UserCommissionRecordDTO recordDTO=new UserCommissionRecordDTO();
                    recordDTO.setLevel(0);
                    recordDTO.setOrderId(order.getId());
                    recordDTO.setCommissionId(partner.getCommissionId());
                    recordDTO.setAmount(amount);
                    recordDTO.setType(0);

                    userCommissionDomain.saveUserCommissionRecord(recordDTO);

                }


                //订单状态完成
                SetOrderStatusDTO setOrderStatusDTO=new SetOrderStatusDTO();
                setOrderStatusDTO.setOrderNo(order.getOrderNo());
                setOrderStatusDTO.setStatus(OrderStatus.FINISHED.getStatus());
                setOrderStatusDTO.setStatusRemark(OrderStatus.FINISHED.getStatusRemark());
                orderService.setOrderStatus(setOrderStatusDTO);

            }
        }

    }






}
