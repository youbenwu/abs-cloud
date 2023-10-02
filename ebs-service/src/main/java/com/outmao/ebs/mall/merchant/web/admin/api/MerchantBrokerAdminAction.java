package com.outmao.ebs.mall.merchant.web.admin.api;


import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.service.MerchantBrokerService;
import com.outmao.ebs.mall.merchant.vo.*;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "account-mall-merchant-broker", tags = "后台-电商-商家-经纪人")
@RestController
@RequestMapping("/api/admin/mall/merchant/broker")
public class MerchantBrokerAdminAction {

	@Autowired
    private MerchantBrokerService merchantBrokerService;


    @PreAuthorize("hasPermission('/mall/merchant/broker','save')")
    @ApiOperation(value = "保存商家经纪人信息", notes = "保存商家经纪人信息")
    @PostMapping("/save")
    public void saveMerchantBroker(MerchantBrokerDTO request) {
        merchantBrokerService.saveMerchantBroker(request);
    }

    @PreAuthorize("hasPermission('/mall/merchant/broker','delete')")
    @ApiOperation(value = "删除商家经纪人信息", notes = "删除商家经纪人信息")
    @PostMapping("/delete")
    public void deleteMerchantBrokerById(Long id) {
        merchantBrokerService.deleteMerchantBrokerById(id);
    }

    @PreAuthorize("hasPermission('/mall/merchant/broker','status')")
    @ApiOperation(value = "设置商家经纪人状态", notes = "设置商家经纪人状态")
    @PostMapping("/setStatus")
    public void setMerchantBrokerStatus(SetMerchantBrokerStatusDTO request) {
        merchantBrokerService.setMerchantBrokerStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/merchant/broker','read')")
    @ApiOperation(value = "读取商家经纪人信息", notes = "读取商家经纪人信息")
    @PostMapping("/get")
    public MerchantBrokerVO getMerchantBrokerVOById(Long id) {
        return merchantBrokerService.getMerchantBrokerVOById(id);
    }


    @PreAuthorize("hasPermission('/mall/merchant/broker','read')")
    @ApiOperation(value = "读取商家经纪人信息列表", notes = "读取商家经纪人信息列表")
    @PostMapping("/page")
    public Page<MerchantBrokerVO> getMerchantBrokerVOPage(GetMerchantBrokerListDTO request, Pageable pageable) {
        return merchantBrokerService.getMerchantBrokerVOPage(request,pageable);
    }


    @ApiOperation(value = "获取经纪人首页推荐列表", notes = "获取经纪人首页推荐列表")
    @PostMapping("/recommend/page")
    public Page<RecommendVO<SimpleMerchantBrokerVO>> getMerchantMemberRecommendVOPage(GetRecommendListDTO request, Pageable pageable){
        return merchantBrokerService.getMerchantMemberRecommendVOPage(request,pageable);
    }



}
