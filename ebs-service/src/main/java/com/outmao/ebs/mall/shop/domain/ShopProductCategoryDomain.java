package com.outmao.ebs.mall.shop.domain;

import com.outmao.ebs.mall.shop.dto.GetShopProductCategoryListDTO;
import com.outmao.ebs.mall.shop.dto.ShopProductCategoryDTO;
import com.outmao.ebs.mall.shop.entity.ShopProductCategory;
import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopProductCategoryDomain {


    public ShopProductCategory saveShopProductCategory(ShopProductCategoryDTO request);

    public void deleteShopProductCategoryById(Long id);

    public List<ShopProductCategoryVO> getShopProductCategoryVOList(GetShopProductCategoryListDTO request);

    public Page<ShopProductCategoryVO> getShopProductCategoryVOPage(GetShopProductCategoryListDTO request, Pageable pageable);


}
