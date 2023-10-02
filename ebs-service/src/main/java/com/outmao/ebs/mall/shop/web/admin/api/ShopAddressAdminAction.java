package com.outmao.ebs.mall.shop.web.admin.api;


import com.outmao.ebs.mall.shop.dto.*;
import com.outmao.ebs.mall.shop.entity.ShopAddress;
import com.outmao.ebs.mall.shop.service.ShopAddressService;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "店铺地址管理",url = "/mall/shop/address",name = "",children = {
                @AccessPermission(title = "保存店铺地址",url = "/mall/shop/address",name = "save"),
                @AccessPermission(title = "删除店铺地址",url = "/mall/shop/address",name = "delete"),
                @AccessPermission(title = "读取店铺地址",url = "/mall/shop/address",name = "read"),
        }),


})


@Api(value = "account-mall-shop-address", tags = "后台-电商-店铺-地址")
@RestController
@RequestMapping("/api/admin/mall/shop/address")
public class ShopAddressAdminAction {


    @Autowired
    private ShopAddressService shopAddressService;


    @PreAuthorize("hasPermission('/mall/shop/address','save')")
    @ApiOperation(value = "保存店铺地址", notes = "保存店铺地址")
    @PostMapping("/save")
    public ShopAddress saveShopAddress(ShopAddressDTO request){
        return shopAddressService.saveShopAddress(request);
    }

    @PreAuthorize("hasPermission('/mall/shop/address','delete')")
    @ApiOperation(value = "删除店铺地址", notes = "删除店铺地址")
    @PostMapping("/delete")
    public void deleteShopAddressById(Long id){
        shopAddressService.deleteShopAddressById(id);
    }

    @PreAuthorize("hasPermission('/mall/shop/address','read')")
    @ApiOperation(value = "获取默认店铺地址", notes = "获取默认店铺地址")
    @PostMapping("/getDefault")
    public ShopAddress getDefaultShopAddress(Long shopId, int type){
        return shopAddressService.getDefaultShopAddress(shopId,type);
    }

    @PreAuthorize("hasPermission('/mall/shop/address','read')")
    @ApiOperation(value = "获取店铺地址列表", notes = "获取店铺地址列表")
    @PostMapping("/page")
    public Page<ShopAddress> getShopAddressPage(GetShopAddressListDTO request, Pageable pageable){
        return shopAddressService.getShopAddressPage(request,pageable);
    }


}
