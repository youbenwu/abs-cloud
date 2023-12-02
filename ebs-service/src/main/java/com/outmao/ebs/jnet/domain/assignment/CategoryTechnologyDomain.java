package com.outmao.ebs.jnet.domain.assignment;

import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.vo.categorytechnology.ProductionCategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 品类
 * 
 * @author yeyi
 * @date 2019年8月27日
 */
public interface CategoryTechnologyDomain {

	Page<ProductionCategoryVO> getProductionCategoryPage(Pageable pageable);

	List<ProductionCategory> findByIdIn(List<Long> ids);
}
