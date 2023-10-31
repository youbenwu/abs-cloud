package com.outmao.ebs.portal.service.aspect;



import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.portal.dto.SetAdvertOrderStatusDTO;
import com.outmao.ebs.portal.service.AdvertService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Aspect
@Component
public class OrderStatusAspect {

	@Autowired
	private AdvertService advertService;


	@Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
	public void setOrderStatus() { }


	@Transactional
	@AfterReturning(returning = "order", pointcut = "setOrderStatus()")
	public void afterSetOrderStatus(JoinPoint jp, Order order) {
        if(order.getType()!=null&&order.getType()== ProductType.ADVERT_CHANNEL.getType()){
			SetAdvertOrderStatusDTO setAdvertOrderStatusDTO=new SetAdvertOrderStatusDTO();
			setAdvertOrderStatusDTO.setOrderNo(order.getOrderNo());
			setAdvertOrderStatusDTO.setStatus(order.getStatus());
			advertService.setAdvertOrderStatus(setAdvertOrderStatusDTO);
		}
	}



}
