package com.outmao.ebs.mall.merchant.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.merchant.vo.*;
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



@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "商家管理",url = "/mall/merchant",name = "",children = {
                @AccessPermission(title = "保存商家信息",url = "/mall/merchant",name = "save"),
                @AccessPermission(title = "删除商家信息",url = "/mall/merchant",name = "delete"),
                @AccessPermission(title = "读取商家信息",url = "/mall/merchant",name = "read"),
                @AccessPermission(title = "设置商家状态",url = "/mall/merchant",name = "status"),
        }),
        @AccessPermissionParent(title = "商家经纪人管理",url = "/mall/merchant/broker",name = "",children = {
                @AccessPermission(title = "保存商家经纪人信息",url = "/mall/merchant/broker",name = "save"),
                @AccessPermission(title = "删除商家经纪人信息",url = "/mall/merchant/broker",name = "delete"),
                @AccessPermission(title = "读取商家经纪人信息",url = "/mall/merchant/broker",name = "read"),
                @AccessPermission(title = "设置商家经纪人状态",url = "/mall/merchant/broker",name = "status"),
        }),
        @AccessPermissionParent(title = "商家客户管理",url = "/mall/merchant/customer",name = "",children = {
                @AccessPermission(title = "保存商家客户信息",url = "/mall/merchant/customer",name = "save"),
                @AccessPermission(title = "删除商家客户信息",url = "/mall/merchant/customer",name = "delete"),
                @AccessPermission(title = "读取商家客户信息",url = "/mall/merchant/customer",name = "read"),
                @AccessPermission(title = "设置商家客户状态",url = "/mall/merchant/customer",name = "status"),
        }),
        @AccessPermissionParent(title = "商家合伙人管理",url = "/mall/merchant/partner",name = "",children = {
                @AccessPermission(title = "保存商家合伙人信息",url = "/mall/merchant/partner",name = "save"),
                @AccessPermission(title = "删除商家合伙人信息",url = "/mall/merchant/partner",name = "delete"),
                @AccessPermission(title = "读取商家合伙人信息",url = "/mall/merchant/partner",name = "read"),
                @AccessPermission(title = "设置商家合伙人状态",url = "/mall/merchant/partner",name = "status"),
        }),
})


@Api(value = "account-mall-merchant", tags = "后台-电商-商家")
@RestController
@RequestMapping("/api/admin/mall/merchant")
public class MerchantAdminAction {

	@Autowired
    private MerchantService merchantService;


    @PreAuthorize("hasPermission('/mall/merchant','save')")
    @ApiOperation(value = "保存商家信息", notes = "保存商家信息")
    @PostMapping("/save")
    public void saveMerchant(@RequestBody MerchantDTO request) {
        merchantService.saveMerchant(request);
    }

    @PreAuthorize("hasPermission('/mall/merchant','status')")
    @ApiOperation(value = "设置商家状态", notes = "设置商家状态")
    @PostMapping("/setStatus")
    public void setMerchantStatus(SetMerchantStatusDTO request) {
        merchantService.setMerchantStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/merchant','read')")
    @ApiOperation(value = "获取商家信息", notes = "获取商家信息")
    @PostMapping("/get")
    public MerchantVO getMerchantVOById(Long id) {
        return merchantService.getMerchantVOById(id);
    }


    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取商家信息", notes = "获取商家信息")
    @PostMapping("/getByUser")
    public MerchantVO getMerchantVOByUserId(Long userId) {
        return merchantService.getMerchantVOByUserId(userId);
    }

    @PostMapping("/getByShop")
    public MerchantVO getMerchantVOByShopId(Long shopId) {
        return merchantService.getMerchantVOByShopId(shopId);
    }


    @PreAuthorize("hasPermission('/mall/merchant','read')")
    @ApiOperation(value = "获取商家信息列表", notes = "获取商家信息列表")
    @PostMapping("/page")
    public Page<MerchantVO> getMerchantVOPage(GetMerchantListDTO request, Pageable pageable) {
        return merchantService.getMerchantVOPage(request,pageable);
    }


}
