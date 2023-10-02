package com.outmao.ebs.mall.merchant.web.api;


import com.outmao.ebs.mall.merchant.dto.GetMerchantCustomerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantCustomerDTO;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.merchant.service.MerchantCustomerService;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "mall-merchant-customer", tags = "电商-商家-客户")
@RestController
@RequestMapping("/api/mall/merchant/customer")
public class MerchantCustomerAction {

	@Autowired
    private MerchantCustomerService merchantCustomerService;


    @ApiOperation(value = "保存商家客户信息、用户绑定经纪人", notes = "保存商家客户信息、用户绑定经纪人")
    @PostMapping("/save")
    public MerchantCustomer saveMerchantCustomer(MerchantCustomerDTO request){
        return merchantCustomerService.saveMerchantCustomer(request);
    }


    @ApiOperation(value = "获取商家客户信息列表", notes = "获取商家客户信息列表")
    @PostMapping("/page")
    public Page<MerchantCustomerVO> getMerchantCustomerVOPage(GetMerchantCustomerListDTO request, Pageable pageable){
        return merchantCustomerService.getMerchantCustomerVOPage(request,pageable);
    }



}
