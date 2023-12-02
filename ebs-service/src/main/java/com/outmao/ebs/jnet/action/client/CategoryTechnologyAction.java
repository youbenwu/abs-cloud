package com.outmao.ebs.jnet.action.client;

import com.outmao.ebs.jnet.domain.assignment.CategoryTechnologyDomain;
import com.outmao.ebs.jnet.vo.categorytechnology.ProductionCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeyi
 * @date 2019年8月31日
 */
@Api(value = "/category", tags = "分类与工艺接口")
@RequestMapping(value = "/api/categoryTechnology")
@RestController
public class CategoryTechnologyAction {
	
	final static private Logger logger = LoggerFactory.getLogger(CategoryTechnologyAction.class);
	
    @Autowired
    private CategoryTechnologyDomain categoryTechnologyDomain;

	@ApiOperation(value = "获取所有分类列表", notes = "获取所有分类列表")
	@PostMapping(value = "/categoryPage")
	public Page<ProductionCategoryVO> getProductionCategoryPage(Pageable pageable){
		return categoryTechnologyDomain.getProductionCategoryPage(pageable);
	}
}
