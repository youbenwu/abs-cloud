package com.outmao.ebs.mall.shop.service;

import com.outmao.ebs.mall.shop.dto.*;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.shop.entity.ShopProductCategory;
import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;
import com.outmao.ebs.mall.shop.vo.ShopVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopService {


    public Shop saveShop(ShopDTO request);


    public Shop setShopStatus(SetShopStatusDTO request);

    public Shop getShopByOrgId(Long orgId);


    public ShopVO getShopVOById(Long id);


    public ShopVO getShopVOByUserId(Long userId);


    public Page<ShopVO> getShopVOPage(GetShopListDTO request, Pageable pageable);


    public ShopProductCategory saveShopProductCategory(ShopProductCategoryDTO request);

    public void deleteShopProductCategoryById(Long id);

    public List<ShopProductCategoryVO> getShopProductCategoryVOList(GetShopProductCategoryListDTO request);

    public Page<ShopProductCategoryVO> getShopProductCategoryVOPage(GetShopProductCategoryListDTO request, Pageable pageable);





}
