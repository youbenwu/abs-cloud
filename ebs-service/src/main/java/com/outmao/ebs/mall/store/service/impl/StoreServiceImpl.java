package com.outmao.ebs.mall.store.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.store.domain.StoreDomain;
import com.outmao.ebs.mall.store.domain.StoreMemberDomain;
import com.outmao.ebs.mall.store.domain.StoreProductDomain;
import com.outmao.ebs.mall.store.dto.*;
import com.outmao.ebs.mall.store.entity.Store;
import com.outmao.ebs.mall.store.entity.StoreMember;
import com.outmao.ebs.mall.store.entity.StoreProduct;
import com.outmao.ebs.mall.store.service.StoreService;
import com.outmao.ebs.mall.store.vo.StoreMemberVO;
import com.outmao.ebs.mall.store.vo.StoreProductVO;
import com.outmao.ebs.mall.store.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StoreServiceImpl extends BaseService implements StoreService {

    @Autowired
    private StoreDomain storeDomain;

    @Autowired
    private StoreMemberDomain storeMemberDomain;


    @Autowired
    private StoreProductDomain storeProductDomain;

    @Override
    public Store saveStore(StoreDTO request) {
        return storeDomain.saveStore(request);
    }

    @Override
    public Store setStoreStatus(SetStoreStatusDTO request) {
        return storeDomain.setStoreStatus(request);
    }

    @Override
    public StoreVO getStoreVOById(Long id) {
        return storeDomain.getStoreVOById(id);
    }

    @Override
    public Page<StoreVO> getStoreVOPage(GetStoreListDTO request, Pageable pageable) {
        return storeDomain.getStoreVOPage(request,pageable);
    }


    @Override
    public StoreMember saveStoreMember(StoreMemberDTO request) {
        return storeMemberDomain.saveStoreMember(request);
    }

    @Override
    public void deleteStoreMemberById(Long id) {
        storeMemberDomain.deleteStoreMemberById(id);
    }

    @Override
    public StoreMember getStoreMember(Long storeId, Long userId) {
        return storeMemberDomain.getStoreMember(storeId,userId);
    }

    @Override
    public Page<StoreMemberVO> getStoreMemberVOPage(GetStoreMemberListDTO request, Pageable pageable) {
        return storeMemberDomain.getStoreMemberVOPage(request,pageable);
    }


    @Override
    public StoreProduct saveStoreProduct(StoreProductDTO request) {
        return storeProductDomain.saveStoreProduct(request);
    }

    @Override
    public void deleteStoreProductById(Long id) {
        storeProductDomain.deleteStoreProductById(id);
    }

    @Override
    public Page<StoreProductVO> getStoreProductVOPage(GetStoreProductListDTO request, Pageable pageable) {
        return storeProductDomain.getStoreProductVOPage(request,pageable);
    }
}
