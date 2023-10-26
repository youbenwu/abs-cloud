package com.outmao.ebs.mall.shop.web.api;


import com.outmao.ebs.mall.shop.dto.*;
import com.outmao.ebs.mall.shop.service.ShopService;
import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;
import com.outmao.ebs.mall.shop.vo.ShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@Api(value = "mall-shop", tags = "电商-店铺")
@RestController
@RequestMapping("/api/mall/shop")
public class ShopAction {


    @Autowired
    private ShopService shopService;



    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取店铺信息", notes = "获取店铺信息")
    @PostMapping("/get")
    public ShopVO getShopVOById(Long id) {
        return shopService.getShopVOById(id);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取店铺信息", notes = "获取店铺信息")
    @PostMapping("/getByUser")
    public ShopVO getShopVOByUserId(Long userId) {
        return shopService.getShopVOByUserId(userId);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取店铺信息列表", notes = "获取店铺信息列表")
    @PostMapping("/page")
    public Page<ShopVO> getShopVOPage(GetShopListDTO request, Pageable pageable) {
        return shopService.getShopVOPage(request,pageable);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取店铺分类信息列表", notes = "获取店铺分类信息列表")
    @PostMapping("/product/category/list")
    public List<ShopProductCategoryVO> getShopCategoryVOList(GetShopProductCategoryListDTO request) {
        return shopService.getShopProductCategoryVOList(request);
    }



}
