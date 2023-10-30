package com.outmao.ebs.wallet.web.api;


import com.outmao.ebs.wallet.dto.CashDTO;
import com.outmao.ebs.wallet.dto.CashPayPrepare;
import com.outmao.ebs.wallet.dto.GetCashListDTO;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.service.CashService;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.TradeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "wallet-cash", tags = "钱包-提现")
@RestController
@RequestMapping("/api/wallet/cash")
public class CashAction {

	@Autowired
    private CashService cashService;


	@ApiOperation(value = "新增提现订单", notes = "新增提现订单")
	@PostMapping("/save")
	public Cash saveCash(CashDTO request){
		return cashService.saveCash(request);
	}


	@ApiOperation(value = "创建支付信息", notes = "创建支付信息")
	@PostMapping("/payPrepare")
	public TradeVO cashPayPrepare(CashPayPrepare request){
		return cashService.cashPayPrepare(request);
	}



	@ApiOperation(value = "获取提现列表", notes = "获取提现列表")
	@PostMapping("/page")
	public Page<CashVO> getCashVOPage(GetCashListDTO request, Pageable pageable){
		return cashService.getCashVOPage(request,pageable);
	}


}
