package com.outmao.ebs.mall.product.service;

import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.mall.product.dto.GetHouseListDTO;
import com.outmao.ebs.mall.product.dto.HouseDTO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.vo.HouseVO;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface HouseService {

    public Product saveHouse(HouseDTO request);
    public HouseVO getHouseVOById(Long id);
    public Page<HouseVO> getHouseVOPage(GetHouseListDTO request, Pageable pageable);
    public Page<SubjectBrowseVO<HouseVO>> getHouseBrowseVOPage(Long userId, Pageable pageable);
    public Page<SubjectCollectionVO<HouseVO>> getHouseCollectionVOPage(Long userId, Pageable pageable);
    public List<HouseVO> getHouseSnapshotVOListByIdIn(Collection<Long> idIn);
    //获取首页房源推荐
    public Page<RecommendVO<HouseVO>> getHouseRecommendVOPage(GetRecommendListDTO request, Pageable pageable);


}
