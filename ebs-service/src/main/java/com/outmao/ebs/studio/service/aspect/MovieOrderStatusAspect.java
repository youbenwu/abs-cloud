package com.outmao.ebs.studio.service.aspect;


import com.alibaba.fastjson.JSON;
import com.outmao.ebs.mall.order.common.constant.OrderSubStatus;
import com.outmao.ebs.mall.order.dto.FinishOrderDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.studio.entity.MovieEpisode;
import com.outmao.ebs.studio.service.MovieService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@Component
public class MovieOrderStatusAspect {


	@Autowired
	private MovieService movieService;

	@Autowired
	private OrderService orderService;

	@Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
	public void setOrderStatus() { }


	@Transactional
	@AfterReturning(returning = "order", pointcut = "setOrderStatus()")
	public void afterSetOrderStatus(JoinPoint jp, Order order) {
		if(order==null)
			return;
		if(order.getType()==null||order.getType()!=ProductType.MOVIE.getType())
			return;

		if(order.isSuccessed()){
			MovieEpisode episode = JSON.parseObject(order.getData(),MovieEpisode.class);
			movieService.saveUserMovieEpisode(order.getOwnerId(),episode.getId());
		}

		FinishOrderDTO dto=new FinishOrderDTO();
		dto.setOrderNo(order.getOrderNo());
		dto.setSubStatus(OrderSubStatus.FINISHED_SELLER.getStatus());
		orderService.finishOrder(dto);

	}




}
