package com.outmao.ebs.mall.distribution.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.distribution.domain.QyDistributionConfigDomain;
import com.outmao.ebs.mall.distribution.dto.QyDistributionConfigDTO;
import com.outmao.ebs.mall.distribution.entity.QyDistributionConfig;
import com.outmao.ebs.mall.distribution.service.QyDistributionConfigService;
import com.outmao.ebs.mall.distribution.vo.QyDistributionConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QyDistributionConfigServiceImpl extends BaseService implements QyDistributionConfigService {


    @Autowired
    private QyDistributionConfigDomain qyDistributionConfigDomain;


    @Override
    public QyDistributionConfig saveQyDistributionConfig(QyDistributionConfigDTO request) {
        return qyDistributionConfigDomain.saveQyDistributionConfig(request);
    }

    @Override
    public void deleteQyDistributionConfigById(Long id) {
        qyDistributionConfigDomain.deleteQyDistributionConfigById(id);
    }

    @Override
    public QyDistributionConfigVO getQyDistributionConfigVO() {
        return qyDistributionConfigDomain.getQyDistributionConfigVO();
    }

    @Override
    public Page<QyDistributionConfigVO> getQyDistributionConfigVOPage(Pageable pageable) {
        return qyDistributionConfigDomain.getQyDistributionConfigVOPage(pageable);
    }


}
