package com.outmao.ebs.mall.store.service;

import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.store.dto.*;
import com.outmao.ebs.mall.store.entity.StoreSkuStockIn;
import com.outmao.ebs.mall.store.entity.StoreSkuStockOut;
import com.outmao.ebs.mall.store.vo.StoreSkuStockInVO;
import com.outmao.ebs.mall.store.vo.StoreSkuStockOutVO;
import com.outmao.ebs.mall.store.vo.StoreSkuVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StoreSkuService {

    public void updateStore(Product product);

    /**
     *
     * 获取商品SKU库存
     *
     * */
    public List<StoreSkuVO> getStoreSkuVOListByProductId(Long productId);

    /**
     *
     * 商品入库
     *
     * */
    public StoreSkuStockIn saveStoreSkuStockIn(StoreSkuStockInDTO request);

    /**
     *
     * 获取商品入库记录列表
     *
     * */
    public Page<StoreSkuStockInVO> getStoreSkuStockInVOPage(GetStoreSkuStockInListDTO request, Pageable pageable);


    /**
     *
     * 商品出库
     *
     * */
    public StoreSkuStockOut saveStoreSkuStockOut(StoreSkuStockOutDTO request);


    /**
     *
     * 设置出库状态
     *
     * */
    public StoreSkuStockOut setStoreSkuStockOutStatus(SetStoreSkuStockOutStatusDTO request);


    /**
     *
     * 获取商品出库记录列表
     *
     * */
    public Page<StoreSkuStockOutVO> getStoreSkuStockOutVOPage(GetStoreSkuStockOutListDTO request, Pageable pageable);



}
