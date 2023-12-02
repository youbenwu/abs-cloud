package com.outmao.ebs.jnet.domain.assignment.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.jnet.dao.factory.ProductionCategoryDao;
import com.outmao.ebs.jnet.domain.assignment.CategoryTechnologyDomain;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.entity.factory.QProductionCategory;
import com.outmao.ebs.jnet.vo.categorytechnology.ProductionCategoryVO;
import com.querydsl.core.types.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 分类与工艺
 * 
 * @author yeyi
 * @date 2019年8月31日
 */
@Transactional
@Component
public class CategoryTechnologyImpl extends BaseDomain implements CategoryTechnologyDomain {
	
    @Autowired
    ProductionCategoryDao productionCategoryDao;

	@Override
	public Page<ProductionCategoryVO> getProductionCategoryPage(Pageable pageable) {
		QProductionCategory e = QProductionCategory.productionCategory;
		return (Page<ProductionCategoryVO>)toPage(QF.select(this.selectProductionCategory(e)).from(e)
				.where(e.level.eq(1))
				.orderBy(e.createTime.desc()).orderBy(e.id.desc())
				,pageable, ProductionCategoryVO.class, e);
	}

	@Override
	public List<ProductionCategory> findByIdIn(List<Long> ids) {
		return productionCategoryDao.findByIdIn(ids);
	}

	private Expression<?>[] selectProductionCategory(QProductionCategory e){
        return new Expression<?>[]{
            e.id,e.parent,e.industry,e.leaf,e.level,e.name,e.image,e.createTime,
        };
    }

}
