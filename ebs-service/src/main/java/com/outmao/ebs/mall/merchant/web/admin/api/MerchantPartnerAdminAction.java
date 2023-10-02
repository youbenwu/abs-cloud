package com.outmao.ebs.mall.merchant.web.admin.api;


import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import com.outmao.ebs.mall.merchant.service.MerchantPartnerService;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.merchant.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "account-mall-merchant-partner", tags = "后台-电商-商家-合伙人")
@RestController
@RequestMapping("/api/admin/mall/merchant/partner")
public class MerchantPartnerAdminAction {

	@Autowired
    private MerchantPartnerService merchantPartnerService;


    @PreAuthorize("hasPermission('/mall/merchant/partner','save')")
    @ApiOperation(value = "保存商家合伙人信息", notes = "保存商家合伙人信息")
    @PostMapping("/save")
    public MerchantPartner saveMerchantPartner(MerchantPartnerDTO request){
        return merchantPartnerService.saveMerchantPartner(request);
    }

    @PreAuthorize("hasPermission('/mall/merchant/partner','delete')")
    @ApiOperation(value = "删除商家合伙人信息", notes = "删除商家合伙人信息")
    @PostMapping("/delete")
    public void deleteMerchantPartnerById(Long id){
        merchantPartnerService.deleteMerchantPartnerById(id);
    }

    @PostAuthorize("hasPermission('/mall/merchant/partner','read')")
    @ApiOperation(value = "获取商家合伙人信息", notes = "获取商家合伙人信息")
    @PostMapping("/get")
    public MerchantPartnerVO getMerchantPartnerVOById(Long id){
        return merchantPartnerService.getMerchantPartnerVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/merchant/partner','read')")
    @ApiOperation(value = "获取商家合伙人信息列表", notes = "获取商家合伙人信息列表")
    @PostMapping("/page")
    public Page<MerchantPartnerVO> getMerchantPartnerVOPage(GetMerchantPartnerListDTO request, Pageable pageable){
        return merchantPartnerService.getMerchantPartnerVOPage(request,pageable);
    }


}
