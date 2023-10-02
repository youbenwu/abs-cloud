package com.outmao.ebs.mall.merchant.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.service.UserCommissionService;
import com.outmao.ebs.mall.merchant.vo.*;
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
        @AccessPermissionParent(title = "佣金提现管理",url = "/mall/commission/cash",name = "",children = {
                @AccessPermission(title = "读取佣金提现信息",url = "/mall/commission/cash",name = "read"),
                @AccessPermission(title = "设置佣金提现状态",url = "/mall/commission/cash",name = "status"),
        }),
        @AccessPermissionParent(title = "佣金管理",url = "/mall/commission",name = "",children = {
                @AccessPermission(title = "读取佣金信息",url = "/mall/commission",name = "read"),
        }),
})



@Api(value = "account-mall-commission", tags = "后台-电商-佣金")
@RestController
@RequestMapping("/api/admin/mall/commission")
public class CommissionAdminAction {

	@Autowired
    private UserCommissionService userCommissionService;


    @PreAuthorize("hasPermission(#request.merchantId,'/mall/commission/cash','status')")
    @ApiOperation(value = "设置佣金提现状态", notes = "设置佣金提现状态")
    @PostMapping("/cash/setStatus")
    public void setUserCommissionCashStatus(SetUserCommissionCashStatusDTO request){
        userCommissionService.setUserCommissionCashStatus(request);
    }


    @PreAuthorize("hasPermission(#request.merchantId,'/mall/commission/cash','read')")
    @ApiOperation(value = "获取佣金提现列表", notes = "获取佣金提现列表")
    @PostMapping("/cash/page")
    public Page<UserCommissionCashVO> getUserCommissionCashVOPage(GetUserCommissionCashListDTO request, Pageable pageable){
        return userCommissionService.getUserCommissionCashVOPage(request,pageable);
    }


    @PreAuthorize("hasPermission(#request.merchantId,'/mall/commission','read')")
    @ApiOperation(value = "获取佣金收益，按时间段", notes = "获取佣金收益，按时间段")
    @PostMapping("/totalAmount")
    public double getUserCommissionTotalAmount(GetUserCommissionTotalAmountDTO request) {
        return userCommissionService.getUserCommissionTotalAmount(request);
    }



}
