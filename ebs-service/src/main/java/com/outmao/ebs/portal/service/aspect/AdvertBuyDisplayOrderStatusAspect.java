package com.outmao.ebs.portal.service.aspect;



import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.portal.common.constant.AdvertBuyDisplayOrderStatus;
import com.outmao.ebs.portal.dto.SetAdvertBuyDisplayOrderStatusDTO;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.service.AdvertBuyDisplayOrderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Aspect
@Component
public class AdvertBuyDisplayOrderStatusAspect {

	@Autowired
	private AdvertBuyDisplayOrderService advertBuyDisplayOrderService;


	@Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
	public void setOrderStatus() { }


	@Transactional
	@AfterReturning(returning = "order", pointcut = "setOrderStatus()")
	public void afterSetOrderStatus(JoinPoint jp, Order order) {
		if(order==null)
			return;
        if(order.getType()!=null&&order.getType()== ProductType.HOTEL_ADVERT_CHANNEL.getType()){

        	if(order.getStatus()== OrderStatus.SUCCESSED.getStatus()){
				SetAdvertBuyDisplayOrderStatusDTO statusDTO=new SetAdvertBuyDisplayOrderStatusDTO();
				statusDTO.setOrderNo(order.getOrderNo());
        		statusDTO.setStatus(AdvertBuyDisplayOrderStatus.WaitAudit.getStatus());
				advertBuyDisplayOrderService.setAdvertBuyDisplayOrderStatus(statusDTO);
			}else if(order.getStatus()== OrderStatus.FINISHED.getStatus()){
				SetAdvertBuyDisplayOrderStatusDTO statusDTO=new SetAdvertBuyDisplayOrderStatusDTO();
				statusDTO.setOrderNo(order.getOrderNo());
				statusDTO.setStatus(AdvertBuyDisplayOrderStatus.Up.getStatus());
				advertBuyDisplayOrderService.setAdvertBuyDisplayOrderStatus(statusDTO);
			}else if(order.getStatus()== OrderStatus.CLOSED.getStatus()){
				SetAdvertBuyDisplayOrderStatusDTO statusDTO=new SetAdvertBuyDisplayOrderStatusDTO();
				statusDTO.setOrderNo(order.getOrderNo());
				statusDTO.setStatus(AdvertBuyDisplayOrderStatus.Cancel.getStatus());
				advertBuyDisplayOrderService.setAdvertBuyDisplayOrderStatus(statusDTO);
			}

		}
	}



}
