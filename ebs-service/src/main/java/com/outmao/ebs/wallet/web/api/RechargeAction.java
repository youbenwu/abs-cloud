package com.outmao.ebs.wallet.web.api;


import com.outmao.ebs.wallet.dto.GetRechargeListDTO;
import com.outmao.ebs.wallet.dto.RechargeDTO;
import com.outmao.ebs.wallet.dto.RechargePayPrepare;
import com.outmao.ebs.wallet.dto.SetRechargeStatusDTO;
import com.outmao.ebs.wallet.entity.Recharge;
import com.outmao.ebs.wallet.service.RechargeService;
import com.outmao.ebs.wallet.vo.RechargeVO;
import com.outmao.ebs.wallet.vo.TradeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "wallet-recharge", tags = "钱包-充值")
@RestController
@RequestMapping("/api/wallet/recharge")
public class RechargeAction {

	@Autowired
    private RechargeService rechargeService;


	@ApiOperation(value = "新增充值订单", notes = "新增充值订单")
	@PostMapping("/save")
	public Recharge saveRecharge(RechargeDTO request){
		return rechargeService.saveRecharge(request);
	}

	@ApiOperation(value = "创建支付信息", notes = "创建支付信息")
	@PostMapping("/payPrepare")
	public TradeVO rechargePayPrepare(RechargePayPrepare request){
		return rechargeService.rechargePayPrepare(request);
	}


	@ApiOperation(value = "获取充值列表", notes = "获取充值列表")
	@PostMapping("/page")
	public Page<RechargeVO> getRechargeVOPage(GetRechargeListDTO request, Pageable pageable){
		return rechargeService.getRechargeVOPage(request,pageable);
	}

	

}
