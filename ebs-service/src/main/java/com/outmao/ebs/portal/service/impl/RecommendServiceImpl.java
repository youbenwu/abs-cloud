package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.portal.domain.RecommendDomain;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.dto.RecommendDTO;
import com.outmao.ebs.portal.entity.Recommend;
import com.outmao.ebs.portal.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl extends BaseService implements RecommendService {


    @Autowired
    private RecommendDomain recommendDomain;

    @Override
    public Recommend saveRecommend(RecommendDTO request) {
        return recommendDomain.saveRecommend(request);
    }

    @Override
    public void deleteRecommendById(Long id) {
        recommendDomain.deleteRecommendById(id);
    }


    @Override
    public Page<Recommend> getRecommendPage(GetRecommendListDTO request, Pageable pageable) {
        return recommendDomain.getRecommendPage(request,pageable);
    }


}
