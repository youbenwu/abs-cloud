package com.outmao.ebs.mall.merchant.web.api;


import com.outmao.ebs.mall.merchant.dto.GetMerchantPartnerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantPartnerDTO;
import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import com.outmao.ebs.mall.merchant.service.MerchantPartnerService;
import com.outmao.ebs.mall.merchant.vo.MerchantPartnerVO;
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

import java.util.List;


@Api(value = "mall-merchant-partner", tags = "电商-商家-合伙人")
@RestController
@RequestMapping("/api/mall/merchant/partner")
public class MerchantPartnerAction {

	@Autowired
    private MerchantPartnerService merchantPartnerService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存商家合伙人信息", notes = "保存商家合伙人信息")
    @PostMapping("/save")
    public MerchantPartner saveMerchantPartner(MerchantPartnerDTO request){
        return merchantPartnerService.saveMerchantPartner(request);
    }

    @PostAuthorize("returnObject.userId.equals(principal.id) or returnObject.broker.userId.equals(principal.id)")
    @ApiOperation(value = "获取商家合伙人信息", notes = "获取商家合伙人信息")
    @PostMapping("/get")
    public MerchantPartnerVO getMerchantPartnerVOById(Long id){
        return merchantPartnerService.getMerchantPartnerVOById(id);
    }


    @ApiOperation(value = "获取商家合伙人信息列表", notes = "获取商家合伙人信息列表")
    @PostMapping("/page")
    public Page<MerchantPartnerVO> getMerchantPartnerVOPage(GetMerchantPartnerListDTO request, Pageable pageable){
        return merchantPartnerService.getMerchantPartnerVOPage(request,pageable);
    }

    @ApiOperation(value = "获取商家合伙人信息列表", notes = "获取商家合伙人信息列表")
    @PostMapping("/listByUser")
    public List<MerchantPartnerVO> getMerchantPartnerVOListByUserId(Long userId) {
        return merchantPartnerService.getMerchantPartnerVOListByUserId(userId);
    }


}
