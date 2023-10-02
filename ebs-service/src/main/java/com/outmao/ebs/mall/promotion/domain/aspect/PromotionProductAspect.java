package com.outmao.ebs.mall.promotion.domain.aspect;


import com.outmao.ebs.mall.product.vo.ProductVO;
import com.outmao.ebs.mall.promotion.domain.GiftsDomain;
import com.outmao.ebs.mall.promotion.vo.GiftsVO;
import com.outmao.ebs.mall.promotion.vo.ProductPromotionVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 给商品设置促销信息
 * 
 * */

@Aspect
@Component
public class PromotionProductAspect {


	@Autowired
	private GiftsDomain giftsDomain;



	@Pointcut("execution(public * com.outmao.ebs.mall.product.domain.ProductDomain.getProductVO*(..))")
	public void GetProductVO() {
	}

	@AfterReturning(returning = "product", pointcut = "GetProductVO()")
	public void afterGetProductVO(JoinPoint jp, ProductVO product) {
        if(product!=null){
        	setPromotion(product);
		}
	}


	private void setPromotion(ProductVO product){
        if(product.getPromotion()==null){
        	product.setPromotion(new ProductPromotionVO());
		}
		GiftsVO giftsVO=giftsDomain.getGiftsVOByProductId(product.getId());
        product.getPromotion().setGifts(giftsVO);
	}


}
