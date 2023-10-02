package com.outmao.ebs.mall.merchant.web.api;



import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "mall-merchant", tags = "电商-商家")
@RestController
@RequestMapping("/api/mall/merchant")
public class MerchantAction {

	@Autowired
    private MerchantService merchantService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存商家信息", notes = "保存商家信息")
    @PostMapping("/save")
    public void saveMerchant(@RequestBody MerchantDTO request) {
        merchantService.saveMerchant(request);
    }


    @PostAuthorize("principal.id.equals(returnObject.userId)")
    @ApiOperation(value = "获取商家信息", notes = "获取商家信息")
    @PostMapping("/get")
    public MerchantVO getMerchantVOById(Long id) {
        return merchantService.getMerchantVOById(id);
    }



}
