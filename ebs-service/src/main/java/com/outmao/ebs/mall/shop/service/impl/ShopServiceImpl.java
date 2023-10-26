package com.outmao.ebs.mall.shop.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.shop.domain.ShopProductCategoryDomain;
import com.outmao.ebs.mall.shop.domain.ShopDomain;
import com.outmao.ebs.mall.shop.dto.*;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.shop.entity.ShopProductCategory;
import com.outmao.ebs.mall.shop.service.ShopService;
import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;
import com.outmao.ebs.mall.shop.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ShopServiceImpl extends BaseService implements ShopService {


    @Autowired
    private ShopDomain shopDomain;

    @Autowired
    private ShopProductCategoryDomain shopProductCategoryDomain;

    @Override
    public Shop saveShop(ShopDTO request) {
        return shopDomain.saveShop(request);
    }

    @Override
    public Shop setShopStatus(SetShopStatusDTO request) {
        return shopDomain.setShopStatus(request);
    }

    @Override
    public ShopVO getShopVOById(Long id) {
        return shopDomain.getShopVOById(id);
    }

    @Override
    public ShopVO getShopVOByUserId(Long userId) {
        return shopDomain.getShopVOByUserId(userId);
    }

    @Override
    public Page<ShopVO> getShopVOPage(GetShopListDTO request, Pageable pageable) {
        return shopDomain.getShopVOPage(request,pageable);
    }

    @Override
    public ShopProductCategory saveShopProductCategory(ShopProductCategoryDTO request) {
        return shopProductCategoryDomain.saveShopProductCategory(request);
    }

    @Override
    public void deleteShopProductCategoryById(Long id) {
        shopProductCategoryDomain.deleteShopProductCategoryById(id);
    }


    @Override
    public List<ShopProductCategoryVO> getShopProductCategoryVOList(GetShopProductCategoryListDTO request) {
        return shopProductCategoryDomain.getShopProductCategoryVOList(request);
    }

    @Override
    public Page<ShopProductCategoryVO> getShopProductCategoryVOPage(GetShopProductCategoryListDTO request, Pageable pageable) {
        return shopProductCategoryDomain.getShopProductCategoryVOPage(request,pageable);
    }
}
