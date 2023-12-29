package com.outmao.ebs.mall.distribution.service;

import com.outmao.ebs.mall.distribution.dto.QyDistributionConfigDTO;
import com.outmao.ebs.mall.distribution.entity.QyDistributionConfig;
import com.outmao.ebs.mall.distribution.vo.QyDistributionConfigVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QyDistributionConfigService {

    public QyDistributionConfig saveQyDistributionConfig(QyDistributionConfigDTO request);

    public void deleteQyDistributionConfigById(Long id);

    public QyDistributionConfigVO getQyDistributionConfigVO();

    public Page<QyDistributionConfigVO> getQyDistributionConfigVOPage(Pageable pageable);



}
