package com.outmao.ebs.wallet.web.admin.api;


import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.service.CashService;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.StatsCashStatusVO;
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


@Api(value = "admin-wallet-cash", tags = "后台-钱包-提现")
@RestController
@RequestMapping("/api/admin/wallet/cash")
public class CashAdminAction {

	@Autowired
    private CashService cashService;


	//Currency
	@PreAuthorize("hasPermission('wallet/cash','delete')")
	@ApiOperation(value = "删除提现", notes = "删除提现")
	@PostMapping("/delete")
	public void deleteCashById(Long id) {
		cashService.deleteCashById(id);
	}

	@PreAuthorize("hasPermission('wallet/cash','status')")
	@ApiOperation(value = "设置提现状态", notes = "设置提现状态")
	@PostMapping("/setStatus")
	public Cash setCashStatus(SetCashStatusDTO request){
		return cashService.setCashStatus(request);
	}

	@PreAuthorize("hasPermission('wallet/cash','read')")
	@ApiOperation(value = "获取提现列表", notes = "获取提现列表")
	@PostMapping("/page")
	public Page<CashVO> getCashVOPage(GetCashListDTO request, Pageable pageable){
		return cashService.getCashVOPage(request,pageable);
	}

	@PreAuthorize("hasPermission('wallet/cash','read')")
	@ApiOperation(value = "提现按状态统计", notes = "提现按状态统计")
	@PostMapping("/stats/status")
	public List<StatsCashStatusVO> getStatsCashStatusVOList() {
		return cashService.getStatsCashStatusVOList();
	}

}
