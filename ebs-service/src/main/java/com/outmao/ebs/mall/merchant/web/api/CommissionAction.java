package com.outmao.ebs.mall.merchant.web.api;


import com.outmao.ebs.mall.merchant.dto.GetUserCommissionCashListDTO;
import com.outmao.ebs.mall.merchant.dto.GetUserCommissionRecordListDTO;
import com.outmao.ebs.mall.merchant.dto.GetUserCommissionTotalAmountDTO;
import com.outmao.ebs.mall.merchant.dto.UserCommissionCashDTO;
import com.outmao.ebs.mall.merchant.service.UserCommissionService;
import com.outmao.ebs.mall.merchant.vo.UserCommissionCashVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionRecordVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Api(value = "mall-commission", tags = "电商-佣金")
@RestController
@RequestMapping("/api/mall/commission")
public class CommissionAction {

	@Autowired
    private UserCommissionService userCommissionService;


    @ApiOperation(value = "获取用户佣金信息", notes = "获取用户佣金信息")
    @PostMapping("/get")
    public UserCommissionVO getUserCommissionVOById(Long id){
        return userCommissionService.getUserCommissionVOById(id);
    }


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取佣金记录列表", notes = "获取佣金记录列表")
    @PostMapping("/record/page")
    public Page<UserCommissionRecordVO> getUserCommissionRecordVOPage(GetUserCommissionRecordListDTO request, Pageable pageable){
        return userCommissionService.getUserCommissionRecordVOPage(request,pageable);
    }

    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "佣金提现", notes = "佣金提现")
    @PostMapping("/cash/save")
    public void saveUserCommissionCash(UserCommissionCashDTO request){
        userCommissionService.saveUserCommissionCash(request);
    }


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取佣金提现列表", notes = "获取佣金提现列表")
    @PostMapping("/cash/page")
    public Page<UserCommissionCashVO> getUserCommissionCashVOPage(GetUserCommissionCashListDTO request, Pageable pageable){
        return userCommissionService.getUserCommissionCashVOPage(request,pageable);
    }


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取佣金收益，按时间段", notes = "获取佣金收益，按时间段")
    @PostMapping("/totalAmount")
    public double getUserCommissionTotalAmount(GetUserCommissionTotalAmountDTO request) {
        return userCommissionService.getUserCommissionTotalAmount(request);
    }




}
