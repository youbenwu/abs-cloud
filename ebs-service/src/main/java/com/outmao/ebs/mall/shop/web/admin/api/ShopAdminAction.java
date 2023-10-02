package com.outmao.ebs.mall.shop.web.admin.api;


import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.shop.dto.*;
import com.outmao.ebs.mall.shop.service.ShopService;
import com.outmao.ebs.mall.shop.vo.ShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "店铺管理",url = "/mall/shop",name = "",children = {
                @AccessPermission(title = "保存店铺信息",url = "/mall/shop",name = "save"),
                @AccessPermission(title = "删除店铺信息",url = "/mall/shop",name = "delete"),
                @AccessPermission(title = "读取店铺信息",url = "/mall/shop",name = "read"),
                @AccessPermission(title = "设置店铺状态",url = "/mall/shop",name = "status"),
        }),
        @AccessPermissionParent(title = "店铺商品分类管理",url = "/mall/shop/product/category",name = "",children = {
                @AccessPermission(title = "保存店铺商品分类",url = "/mall/shop/product/category",name = "save"),
                @AccessPermission(title = "删除店铺商品分类",url = "/mall/shop/product/category",name = "delete"),
                @AccessPermission(title = "读取店铺商品分类",url = "/mall/shop/product/category",name = "read"),
        }),

})


@Api(value = "account-mall-shop", tags = "后台-电商-店铺")
@RestController
@RequestMapping("/api/admin/mall/shop")
public class ShopAdminAction {


    @Autowired
    private ShopService shopService;


    @PreAuthorize("hasPermission('/mall/shop','save')")
    @ApiOperation(value = "保存店铺信息", notes = "保存店铺信息")
    @PostMapping("/save")
    public void saveShop(@RequestBody ShopDTO request) {
        shopService.saveShop(request);
    }


    @PreAuthorize("hasPermission('/mall/shop','status')")
    @ApiOperation(value = "设置店铺状态", notes = "保存店铺状态")
    @PostMapping("/setStatus")
    public void setShopStatus(SetShopStatusDTO request) {
        shopService.setShopStatus(request);
    }


    @PreAuthorize("hasPermission('/mall/shop','read')")
    @ApiOperation(value = "获取店铺信息", notes = "获取店铺信息")
    @PostMapping("/get")
    public ShopVO getShopVOById(Long id) {
        return shopService.getShopVOById(id);
    }


    @PreAuthorize("hasPermission('/mall/shop','read')")
    @ApiOperation(value = "获取店铺信息", notes = "获取店铺信息")
    @PostMapping("/getByUser")
    public ShopVO getShopVOByUserId(Long userId) {
        return shopService.getShopVOByUserId(userId);
    }


    @PreAuthorize("hasPermission('/mall/shop','read')")
    @ApiOperation(value = "获取店铺信息列表", notes = "获取店铺信息列表")
    @PostMapping("/page")
    public Page<ShopVO> getShopVOPage(GetShopListDTO request, Pageable pageable) {
        return shopService.getShopVOPage(request,pageable);
    }


    @PreAuthorize("hasPermission('/mall/shop/product/category','save')")
    @ApiOperation(value = "保存店铺分类信息", notes = "保存店铺分类信息")
    @PostMapping("/product/category/save")
    public void saveShopProductCategory(ShopProductCategoryDTO request) {
         shopService.saveShopProductCategory(request);
    }

    @PreAuthorize("hasPermission('/mall/shop/product/category','delete')")
    @ApiOperation(value = "删除店铺分类信息", notes = "删除店铺分类信息")
    @PostMapping("/product/category/delete")
    public void deleteShopProductCategoryById(Long shopId,Long id) {
        shopService.deleteShopProductCategoryById(id);
    }

    @PreAuthorize("hasPermission('/mall/shop/product/category','read')")
    @ApiOperation(value = "获取店铺分类信息列表", notes = "获取店铺分类信息列表")
    @PostMapping("/product/category/list")
    public List<ShopProductCategoryVO> getShopProductCategoryVOList(Long shopId) {
        return shopService.getShopProductCategoryVOList(shopId);
    }


}
