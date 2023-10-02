package com.outmao.ebs.mall.shop.domain;

import com.outmao.ebs.mall.shop.dto.ShopProductCategoryDTO;
import com.outmao.ebs.mall.shop.entity.ShopProductCategory;
import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;

import java.util.List;

public interface ShopProductCategoryDomain {


    public ShopProductCategory saveShopProductCategory(ShopProductCategoryDTO request);

    public void deleteShopProductCategoryById(Long id);

    public List<ShopProductCategoryVO> getShopProductCategoryVOList(Long shopId);


}
