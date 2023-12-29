package com.outmao.ebs.mall.distribution.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.distribution.dao.QyDistributionConfigDao;
import com.outmao.ebs.mall.distribution.domain.QyDistributionConfigDomain;
import com.outmao.ebs.mall.distribution.domain.conver.QyDistributionConfigVOConver;
import com.outmao.ebs.mall.distribution.dto.QyDistributionConfigDTO;
import com.outmao.ebs.mall.distribution.entity.QQyDistributionConfig;
import com.outmao.ebs.mall.distribution.entity.QyDistributionConfig;
import com.outmao.ebs.mall.distribution.vo.QyDistributionConfigVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Component
public class QyDistributionConfigDomainImpl extends BaseDomain implements QyDistributionConfigDomain {



    @Autowired
    private QyDistributionConfigDao qyDistributionConfigDao;

    private QyDistributionConfigVOConver qyDistributionConfigVOConver=new QyDistributionConfigVOConver();


    @CacheEvict(value = "cache_QyDistributionConfig", allEntries = true)
    @Transactional
    @Override
    public QyDistributionConfig saveQyDistributionConfig(QyDistributionConfigDTO request) {
        QyDistributionConfig config=request.getId()==null?null:qyDistributionConfigDao.getOne(request.getId());

        if(config==null){
            config=new QyDistributionConfig();
            config.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,config);

        config.setUpdateTime(new Date());
        qyDistributionConfigDao.save(config);

        return config;
    }

    @CacheEvict(value = "cache_QyDistributionConfig", allEntries = true)
    @Transactional
    @Override
    public void deleteQyDistributionConfigById(Long id) {
        qyDistributionConfigDao.deleteById(id);
    }

    @Cacheable(value = "cache_QyDistributionConfig", key = "method.name")
    @Override
    public QyDistributionConfigVO getQyDistributionConfigVO() {
        QQyDistributionConfig e=QQyDistributionConfig.qyDistributionConfig;
        List<QyDistributionConfigVO> list=queryList(e,e.status.eq(0),qyDistributionConfigVOConver);
        if(list.size()>0)
            return list.get(0);
        return null;
    }

    @Override
    public Page<QyDistributionConfigVO> getQyDistributionConfigVOPage(Pageable pageable) {
        QQyDistributionConfig e=QQyDistributionConfig.qyDistributionConfig;
        Predicate p=null;
        return queryPage(e,p,qyDistributionConfigVOConver,pageable);
    }


}
