package com.outmao.ebs.mall.merchant.web.admin.api;


import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.service.MerchantCustomerService;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "account-mall-merchant-customer", tags = "后台-电商-商家-客户")
@RestController
@RequestMapping("/api/admin/mall/merchant/customer")
public class MerchantCustomerAdminAction {

	@Autowired
    private MerchantCustomerService merchantCustomerService;



    @PreAuthorize("hasPermission('/mall/merchant/customer','save')")
    @ApiOperation(value = "保存商家客户信息", notes = "保存商家客户信息")
    @PostMapping("/save")
    public void saveMerchantCustomer(MerchantCustomerDTO request){
        merchantCustomerService.saveMerchantCustomer(request);
    }

    @PreAuthorize("hasPermission('/mall/merchant/customer','delete')")
    @ApiOperation(value = "删除商家客户信息", notes = "删除商家客户信息")
    @PostMapping("/delete")
    public void deleteMerchantCustomerById(Long id){
        merchantCustomerService.deleteMerchantCustomerById(id);
    }

    @PreAuthorize("hasPermission('/mall/merchant/customer','read')")
    @ApiOperation(value = "获取商家客户信息", notes = "获取商家客户信息")
    @PostMapping("/get")
    public MerchantCustomerVO getMerchantCustomerVOById(Long id){
        return merchantCustomerService.getMerchantCustomerVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/merchant/customer','read')")
    @ApiOperation(value = "获取商家客户信息列表", notes = "获取商家客户信息列表")
    @PostMapping("/page")
    public Page<MerchantCustomerVO> getMerchantCustomerVOPage(GetMerchantCustomerListDTO request, Pageable pageable){
        return merchantCustomerService.getMerchantCustomerVOPage(request,pageable);
    }




}
