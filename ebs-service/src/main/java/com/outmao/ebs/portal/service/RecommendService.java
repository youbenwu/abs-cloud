package com.outmao.ebs.portal.service;

import com.outmao.ebs.common.vo.IItem;
import com.outmao.ebs.common.vo.ItemListGetter;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.dto.RecommendDTO;
import com.outmao.ebs.portal.entity.Recommend;
import com.outmao.ebs.portal.vo.RecommendVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecommendService {

    public Recommend saveRecommend(RecommendDTO request);

    public void deleteRecommendById(Long id);

    public Page<Recommend> getRecommendPage(GetRecommendListDTO request, Pageable pageable);

    public <T extends IItem>Page<RecommendVO<T>> getRecommendVOPage(GetRecommendListDTO request, ItemListGetter<T> itemListGetter, Pageable pageable);


}
