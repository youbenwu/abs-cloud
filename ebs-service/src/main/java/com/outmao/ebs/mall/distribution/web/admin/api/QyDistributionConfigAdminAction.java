package com.outmao.ebs.mall.distribution.web.admin.api;


import com.outmao.ebs.mall.distribution.dto.QyDistributionConfigDTO;
import com.outmao.ebs.mall.distribution.entity.QyDistributionConfig;
import com.outmao.ebs.mall.distribution.service.QyDistributionConfigService;
import com.outmao.ebs.mall.distribution.vo.QyDistributionConfigVO;
import com.outmao.ebs.wallet.dto.GetCashListDTO;
import com.outmao.ebs.wallet.dto.SetCashStatusDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "admin-mall-distribution-config-qy", tags = "后台-电商-分销-配置-迁眼")
@RestController
@RequestMapping("/api/admin/mall/distribution/config/qy")
public class QyDistributionConfigAdminAction {

	@Autowired
    private QyDistributionConfigService qyDistributionConfigService;



	@PreAuthorize("hasPermission('mall/distribution/config','save')")
	@ApiOperation(value = "保存迁眼分销配置", notes = "保存迁眼分销配置")
	@PostMapping("/save")
	public void saveQyDistributionConfig(@RequestBody QyDistributionConfigDTO request){
		qyDistributionConfigService.saveQyDistributionConfig(request);
	}

	@PreAuthorize("hasPermission('mall/distribution/config','delete')")
	@ApiOperation(value = "删除迁眼分销配置", notes = "删除迁眼分销配置")
	@PostMapping("/delete")
	public void deleteQyDistributionConfigById(Long id){
		qyDistributionConfigService.deleteQyDistributionConfigById(id);
	}

	@PreAuthorize("hasPermission('mall/distribution/config','read')")
	@ApiOperation(value = "获取迁眼分销配置", notes = "获取迁眼分销配置")
	@PostMapping("/page")
	public Page<QyDistributionConfigVO> getQyDistributionConfigVOPage(Pageable pageable){
		return qyDistributionConfigService.getQyDistributionConfigVOPage(pageable);
	}


}
