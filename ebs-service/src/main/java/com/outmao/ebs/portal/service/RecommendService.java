package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.dto.RecommendDTO;
import com.outmao.ebs.portal.entity.Recommend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecommendService {

    public Recommend saveRecommend(RecommendDTO request);

    public void deleteRecommendById(Long id);

    public Page<Recommend> getRecommendPage(GetRecommendListDTO request, Pageable pageable);


}
