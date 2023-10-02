package com.outmao.ebs.mall.store.service;

import com.outmao.ebs.mall.store.dto.*;
import com.outmao.ebs.mall.store.entity.Store;
import com.outmao.ebs.mall.store.entity.StoreMember;
import com.outmao.ebs.mall.store.entity.StoreProduct;
import com.outmao.ebs.mall.store.vo.StoreMemberVO;
import com.outmao.ebs.mall.store.vo.StoreProductVO;
import com.outmao.ebs.mall.store.vo.StoreVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService {

    public Store saveStore(StoreDTO request);

    public Store setStoreStatus(SetStoreStatusDTO request);

    public StoreVO getStoreVOById(Long id);

    public Page<StoreVO> getStoreVOPage(GetStoreListDTO request, Pageable pageable);


    public StoreMember saveStoreMember(StoreMemberDTO request);

    public void deleteStoreMemberById(Long id);

    public StoreMember getStoreMember(Long storeId, Long userId);

    public Page<StoreMemberVO> getStoreMemberVOPage(GetStoreMemberListDTO request, Pageable pageable);


    public StoreProduct saveStoreProduct(StoreProductDTO request);

    public void deleteStoreProductById(Long id);

    public Page<StoreProductVO> getStoreProductVOPage(GetStoreProductListDTO request, Pageable pageable);


}
