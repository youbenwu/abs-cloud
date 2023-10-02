package com.outmao.ebs.mall.product.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.vo.DataItemGetter;
import com.outmao.ebs.bbs.common.data.GetSubjectItemList;
import com.outmao.ebs.bbs.domain.SubjectDomain;
import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.mall.product.domain.HouseDomain;
import com.outmao.ebs.mall.product.dto.GetHouseListDTO;
import com.outmao.ebs.mall.product.dto.HouseDTO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.service.HouseService;
import com.outmao.ebs.mall.product.vo.HouseVO;
import com.outmao.ebs.portal.domain.RecommendDomain;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class HouseServiceImpl extends BaseService implements HouseService {

    @Autowired
    private HouseDomain houseDomain;


    @Autowired
    private SubjectDomain subjectDomain;

    @Autowired
    private RecommendDomain recommendDomain;


    @Override
    public Product saveHouse(HouseDTO request) {
        return houseDomain.saveHouse(request);
    }


    @Override
    public HouseVO getHouseVOById(Long id) {
        return houseDomain.getHouseVOById(id);
    }

    @Override
    public Page<HouseVO> getHouseVOPage(GetHouseListDTO request, Pageable pageable) {
        return houseDomain.getHouseVOPage(request,pageable);
    }

    @Override
    public Page<SubjectBrowseVO<HouseVO>> getHouseBrowseVOPage(Long userId, Pageable pageable) {
        Page<SubjectBrowseVO<HouseVO>> page=subjectDomain.getSubjectBrowseVOPage(userId, "Product", new GetSubjectItemList<HouseVO>() {
            @Override
            public List<HouseVO> getSubjectItemList(Collection<Long> idIn) {
                return houseDomain.getHouseVOListByIdIn(idIn);
            }
        },pageable);

        return page;
    }

    @Override
    public Page<SubjectCollectionVO<HouseVO>> getHouseCollectionVOPage(Long userId, Pageable pageable) {
        Page<SubjectCollectionVO<HouseVO>> page=subjectDomain.getSubjectCollectionVOPage(userId, "Product", new GetSubjectItemList<HouseVO>() {
            @Override
            public List<HouseVO> getSubjectItemList(Collection<Long> idIn) {
                return houseDomain.getHouseVOListByIdIn(idIn);
            }
        },pageable);

        return page;
    }

    @Override
    public List<HouseVO> getHouseSnapshotVOListByIdIn(Collection<Long> idIn) {
        return houseDomain.getHouseSnapshotVOListByIdIn(idIn);
    }

    @Override
    public Page<RecommendVO<HouseVO>> getHouseRecommendVOPage(GetRecommendListDTO request, Pageable pageable) {
        return recommendDomain.getRecommendVOPage(request,new DataItemGetter<HouseVO>() {
            @Override
            public List<HouseVO> getDataItemListByIdIn(Collection<Long> idIn) {
                return houseDomain.getHouseVOListByIdIn(idIn);
            }
        },pageable);
    }


}
