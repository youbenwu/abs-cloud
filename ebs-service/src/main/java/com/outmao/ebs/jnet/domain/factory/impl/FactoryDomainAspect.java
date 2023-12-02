package com.outmao.ebs.jnet.domain.factory.impl;



import com.outmao.ebs.bbs.dao.SubjectDao;
import com.outmao.ebs.bbs.dao.SubjectStatsDao;
import com.outmao.ebs.jnet.vo.factory.FactoryVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * 过滤用户数据
 * 数据统计
 * 
 * */

@Aspect
@Component
public class FactoryDomainAspect {

	@Autowired
    SubjectDao subjectDao;
    @Autowired
	SubjectStatsDao subjectStatsDao;

	@Pointcut("execution(public *  com.outmao.ebs.jnet.domain.factory.FactoryDomain.getFactoryVOBy*(..))")
	public void getFactoryVO() {

	}

	@AfterReturning(returning = "vo", pointcut = "getFactoryVO()")
	public void afterGetFactoryVO(JoinPoint jp, FactoryVO vo) {
		if (vo == null)
			return;
		// 浏览加1
        subjectStatsDao.browsesAdd(vo.getSubjectId(), 1,new Date());
	}


}
