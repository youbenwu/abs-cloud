package com.outmao.ebs.mall.store.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.store.domain.StoreSkuDomain;
import com.outmao.ebs.mall.store.dto.*;
import com.outmao.ebs.mall.store.entity.StoreSkuStockIn;
import com.outmao.ebs.mall.store.entity.StoreSkuStockOut;
import com.outmao.ebs.mall.store.service.StoreSkuService;
import com.outmao.ebs.mall.store.vo.StoreSkuStockInVO;
import com.outmao.ebs.mall.store.vo.StoreSkuStockOutVO;
import com.outmao.ebs.mall.store.vo.StoreSkuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreSkuServiceImpl extends BaseService implements StoreSkuService {


    @Autowired
    private StoreSkuDomain storeSkuDomain;


    @Override
    public void updateStore(Product product) {
        storeSkuDomain.updateStore(product);
    }

    @Override
    public List<StoreSkuVO> getStoreSkuVOListByProductId(Long productId) {
        return storeSkuDomain.getStoreSkuVOListByProductId(productId);
    }

    @Override
    public StoreSkuStockIn saveStoreSkuStockIn(StoreSkuStockInDTO request) {
        return storeSkuDomain.saveStoreSkuStockIn(request);
    }

    @Override
    public Page<StoreSkuStockInVO> getStoreSkuStockInVOPage(GetStoreSkuStockInListDTO request, Pageable pageable) {
        return storeSkuDomain.getStoreSkuStockInVOPage(request,pageable);
    }

    @Override
    public StoreSkuStockOut saveStoreSkuStockOut(StoreSkuStockOutDTO request) {
        return storeSkuDomain.saveStoreSkuStockOut(request);
    }

    @Override
    public StoreSkuStockOut setStoreSkuStockOutStatus(SetStoreSkuStockOutStatusDTO request) {
        return storeSkuDomain.setStoreSkuStockOutStatus(request);
    }

    @Override
    public Page<StoreSkuStockOutVO> getStoreSkuStockOutVOPage(GetStoreSkuStockOutListDTO request, Pageable pageable) {
        return storeSkuDomain.getStoreSkuStockOutVOPage(request,pageable);
    }
}
