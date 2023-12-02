package com.outmao.ebs.jnet.domain.index.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.jnet.dao.index.FunctionDao;
import com.outmao.ebs.jnet.domain.index.IndexDomain;
import com.outmao.ebs.jnet.entity.index.Function;
import com.outmao.ebs.jnet.entity.index.QFunction;
import com.outmao.ebs.jnet.entity.index.QIndexBanner;
import com.outmao.ebs.jnet.service.config.ConfigAssignmentService;
import com.outmao.ebs.jnet.vo.index.BannerVO;
import com.outmao.ebs.jnet.vo.index.FunctionVO;
import com.outmao.ebs.jnet.vo.index.IndexVO;
import com.querydsl.core.types.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;


/**
 * @author yeyi
 * @date 2019年9月23日
 */
@Transactional
@Component
public class IndexDomainImpl extends BaseDomain implements IndexDomain {

    private static final Logger log = LoggerFactory.getLogger(IndexDomainImpl.class);

	@Autowired
	private FunctionDao functionDao;
	
	@Autowired
	ConfigAssignmentService configAssignmentService;

	private Expression<?>[] selectQFunction(QFunction e){
		return new Expression<?>[]{
				e.id,e.createTime, e.isDeleted, e.name, e.updateTime, e.useCount, e.imgUrl, e.pageUrl, e.bannerUrl
		};
	}
	
	private Expression<?>[] selectQIndexBanner(QIndexBanner e){
		return new Expression<?>[]{
				e.id,e.createTime, e.isDeleted, e.updateTime, e.imgUrl, e.remark
		};
	}

	@Override
	public Page<FunctionVO> getFunctionList(Pageable pageable) {
		QFunction e = QFunction.function;
		Page<FunctionVO> page = (Page<FunctionVO>)toPage(QF.select(this.selectQFunction(e)).from(e)
						.where(e.isDeleted.eq(false))
						.orderBy(e.updateTime.asc())
				,pageable, FunctionVO.class, e);

		return page;
	}

    @Override
    public void randomAddFunctionUseCount() {
        List<Function> all = functionDao.findAll();
        if(CollectionUtils.isEmpty(all)){
            return;
        }

        long startTime = System.currentTimeMillis();
        final int MAX_ADD = Integer.parseInt(configAssignmentService.getIndexFunctionUseCountAddMax());
        Random r = new Random(System.currentTimeMillis());

        all.stream().forEach(e->{
            final Long useCountOld = e.getUseCount();
            Long random = (long)r.nextInt(MAX_ADD) + 1;
            Long useCount = useCountOld + random;
            e.setUseCount(useCount);
            functionDao.save(e);
        });
        log.info("randomAddFunctionUseCoun spendTime: {}", System.currentTimeMillis()-startTime);
    }

	@Override
	public Page<BannerVO> getBannerList(Pageable pageable) {
		QIndexBanner e = QIndexBanner.indexBanner;
		Page<BannerVO> page = (Page<BannerVO>)toPage(QF.select(this.selectQIndexBanner(e)).from(e)
						.where(e.isDeleted.eq(false))
						.orderBy(e.updateTime.desc())
				,pageable, BannerVO.class, e);

		return page;
	}

	@Override
	public IndexVO getIndex() {
		IndexVO result = new IndexVO();
		final PageRequest pageable = PageRequest.of(0, 99999);
		result.setBanners(this.getBannerList(pageable));
		result.setFunctions(this.getFunctionList(pageable));
		return result;
	}
}
