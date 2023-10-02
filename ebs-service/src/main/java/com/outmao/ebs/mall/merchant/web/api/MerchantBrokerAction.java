package com.outmao.ebs.mall.merchant.web.api;


import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.service.MerchantBrokerService;
import com.outmao.ebs.mall.merchant.vo.MerchantBrokerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
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
import java.util.List;


@Api(value = "mall-merchant-broker", tags = "电商-商家-经纪人")
@RestController
@RequestMapping("/api/mall/merchant/broker")
public class MerchantBrokerAction {

	@Autowired
    private MerchantBrokerService merchantBrokerService;


    @PreAuthorize("principal.id.equals(#request.userId) and #request.id!=null")
    @ApiOperation(value = "保存商家经纪人信息", notes = "保存商家经纪人信息")
    @PostMapping("/save")
    public void saveMerchantBroker(MerchantBrokerDTO request) {
        merchantBrokerService.saveMerchantBroker(request);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取商家经纪人信息", notes = "获取商家经纪人信息")
    @PostMapping("/get")
    public MerchantBrokerVO getMerchantBrokerVOById(Long id) {
        return merchantBrokerService.getMerchantBrokerVOById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "读取商家经纪人信息列表", notes = "读取商家经纪人信息列表")
    @PostMapping("/page")
    public Page<MerchantBrokerVO> getMerchantBrokerVOPage(GetMerchantBrokerListDTO request, Pageable pageable) {
        return merchantBrokerService.getMerchantBrokerVOPage(request,pageable);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取商家经纪人信息列表", notes = "获取商家经纪人信息列表")
    @PostMapping("/listByUser")
    public List<MerchantBrokerVO> getMerchantMemberVOListByUserId(Long userId) {
        return merchantBrokerService.getMerchantBrokerVOListByUserId(userId);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取商家经纪人信息列表", notes = "获取商家经纪人信息列表")
    @PostMapping("/listByCustomerUser")
    public List<MerchantBrokerVO> getMerchantBrokerVOListByCustomerUserId(Long userId) {
        return merchantBrokerService.getMerchantBrokerVOListByCustomerUserId(userId);
    }

    //为房源获取一个经纪人服务
    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "为商品获取一个经纪人服务", notes = "为商品获取一个经纪人服务")
    @PostMapping("/getForService")
    public MerchantBrokerVO getMerchantBrokerVOForService(GetMerchantBrokerForServiceDTO request){
        return merchantBrokerService.getMerchantBrokerVOForService(request);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取经纪人首页推荐列表", notes = "获取经纪人首页推荐列表")
    @PostMapping("/recommend/page")
    public Page<RecommendVO<SimpleMerchantBrokerVO>> getMerchantMemberRecommendVOPage(GetRecommendListDTO request, Pageable pageable){
        return merchantBrokerService.getMerchantMemberRecommendVOPage(request,pageable);
    }



}
