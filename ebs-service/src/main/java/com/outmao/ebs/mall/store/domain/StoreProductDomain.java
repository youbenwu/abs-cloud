package com.outmao.ebs.mall.store.domain;

import com.outmao.ebs.mall.store.dto.GetStoreProductListDTO;
import com.outmao.ebs.mall.store.dto.StoreProductDTO;
import com.outmao.ebs.mall.store.entity.StoreProduct;
import com.outmao.ebs.mall.store.vo.StoreProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreProductDomain {

    public StoreProduct saveStoreProduct(StoreProductDTO request);

    public void deleteStoreProductById(Long id);

    public Page<StoreProductVO> getStoreProductVOPage(GetStoreProductListDTO request, Pageable pageable);

    public List<String> getStoreTitleListByProductId(Long productId);


}
