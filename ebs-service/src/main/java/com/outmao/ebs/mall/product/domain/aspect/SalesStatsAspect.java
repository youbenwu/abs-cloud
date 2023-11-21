package com.outmao.ebs.mall.product.domain.aspect;



import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.portal.dto.SetAdvertOrderStatusDTO;
import com.outmao.ebs.portal.service.AdvertService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 销售量统计
 *
 * */

@Aspect
@Component
public class SalesStatsAspect {

	@Autowired
	private ProductDao productDao;


	@Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
	public void setOrderStatus() { }


	@Transactional
	@AfterReturning(returning = "order", pointcut = "setOrderStatus()")
	public void afterSetOrderStatus(JoinPoint jp, Order order) {
        if(order!=null&&order.getStatus()== OrderStatus.SUCCESSED.getStatus()){
        	order.getProducts().forEach(p->{
        		if(p.getProductId()!=null) {
					productDao.salesAdd(p.getProductId(), p.getQuantity());
				}
			});
		}
	}



}
