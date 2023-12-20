package com.outmao.ebs.wallet.web.admin.api;


import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.entity.Recharge;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.service.CashService;
import com.outmao.ebs.wallet.service.RechargeService;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.RechargeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-wallet-recharge", tags = "后台-钱包-充值")
@RestController
@RequestMapping("/api/admin/wallet/recharge")
public class RechargeAdminAction {

	@Autowired
    private RechargeService rechargeService;

	@Autowired
	private TradeService tradeService;


	@PreAuthorize("hasPermission(null,'wallet/recharge','recharge')")
	@ApiOperation(value = "充值", notes = "充值")
	@PostMapping("/tradeRecharge")
	public Trade tradeRecharge(TradeRechargeDTO request){
		return tradeService.tradeRecharge(request);
	}


	@PreAuthorize("hasPermission('wallet/recharge','status')")
	@ApiOperation(value = "设置充值状态", notes = "设置充值状态")
	@PostMapping("/setStatus")
	public Recharge setRechargeStatus(SetRechargeStatusDTO request){
		return rechargeService.setRechargeStatus(request);
	}



	@PreAuthorize("hasPermission('wallet/recharge','read')")
	@ApiOperation(value = "获取充值列表", notes = "获取充值列表")
	@PostMapping("/page")
	public Page<RechargeVO> getRechargeVOPage(GetRechargeListDTO request, Pageable pageable){
		return rechargeService.getRechargeVOPage(request,pageable);
	}



}
