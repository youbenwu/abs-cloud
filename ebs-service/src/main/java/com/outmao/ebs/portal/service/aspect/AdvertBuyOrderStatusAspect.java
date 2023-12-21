package com.outmao.ebs.portal.service.aspect;


import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.portal.common.constant.AdvertBuyOrderStatus;
import com.outmao.ebs.portal.dto.SetAdvertBuyOrderStatusDTO;
import com.outmao.ebs.portal.service.AdvertBuyOrderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@Component
public class AdvertBuyOrderStatusAspect {


	@Autowired
	private AdvertBuyOrderService advertBuyOrderService;

	@Autowired
	private OrderService orderService;

	@Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
	public void setOrderStatus() { }


	@Transactional
	@AfterReturning(returning = "order", pointcut = "setOrderStatus()")
	public void afterSetOrderStatus(JoinPoint jp, Order order) {
		if(order==null)
			return;
        if(order.getType()!=null&&order.getType()== ProductType.HOTEL_ADVERT_CHANNEL.getType()){

        	if(order.getStatus()== OrderStatus.SUCCESSED.getStatus()){

				SetAdvertBuyOrderStatusDTO statusDTO=new SetAdvertBuyOrderStatusDTO();
				statusDTO.setOrderNo(order.getOrderNo());
        		statusDTO.setStatus(AdvertBuyOrderStatus.Finish.getStatus());
				advertBuyOrderService.setAdvertBuyOrderStatus(statusDTO);

				SetOrderStatusDTO setOrderStatusDTO=new SetOrderStatusDTO();
				setOrderStatusDTO.setOrderNo(order.getOrderNo());
				setOrderStatusDTO.setStatus(OrderStatus.FINISHED.getStatus());
				setOrderStatusDTO.setStatusRemark(OrderStatus.FINISHED.getStatusRemark());
				orderService.setOrderStatus(setOrderStatusDTO);
				
			}else if(order.getStatus()== OrderStatus.CLOSED.getStatus()){

				SetAdvertBuyOrderStatusDTO statusDTO=new SetAdvertBuyOrderStatusDTO();
				statusDTO.setOrderNo(order.getOrderNo());
				statusDTO.setStatus(AdvertBuyOrderStatus.Cancel.getStatus());
				advertBuyOrderService.setAdvertBuyOrderStatus(statusDTO);

			}

		}
	}



}
