package com.outmao.ebs.jnet.action.client;


import com.outmao.ebs.jnet.dto.storage.DemandBillParamsDTO;
import com.outmao.ebs.jnet.entity.storage.DemandBill;
import com.outmao.ebs.jnet.service.storage.StorageService;
import com.outmao.ebs.jnet.vo.storage.DemandBillVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleSpecVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleVO;
import com.outmao.ebs.jnet.vo.storage.StorageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "/order", tags = "织网-仓库模块接口")
@RestController("ZStorageAction")
@RequestMapping("/api/storage")
public class StorageAction {

	@Autowired
    StorageService storageService;

	@ApiOperation(value = "加单接口", notes = "加单接口")
	@PostMapping(value = "/demandbill/save")
	public DemandBill saveDemandBill(@RequestBody DemandBillParamsDTO params) {
		return storageService.saveDemandBill(params);
	}

	@ApiOperation(value = "加单记录接口", notes = "加单记录接口")
	@PostMapping(value = "/demandbill/page")
	public Page<DemandBillVO> getDemandBillVOPage(Long storageId, Pageable pageable) {
		return storageService.getDemandBillVOPage(storageId,pageable);
	}

	@ApiOperation(value = "生产仓库信息接口", notes = "生产仓库信息接口")
	@PostMapping(value = "/get")
	public StorageVO getStorageVOById(Long id) {
		return storageService.getStorageVOById(id);
	}

	@ApiOperation(value = "获取仓库款型列表接口", notes = "获取仓库款型列表接口")
	@PostMapping(value = "/style/list")
	public List<StorageStyleVO> getStorageStyleVOListByStorageId(Long storageId) {
		return storageService.getStorageStyleVOListByStorageId(storageId);
	}

	@ApiOperation(value = "获取款型规格列表接口", notes = "获取款型规格列表接口")
	@PostMapping(value = "/style/spec/list")
	public List<StorageStyleSpecVO> getStorageStyleSpecListVOByStyleId(Long styleId) {
		return storageService.getStorageStyleSpecListVOByStyleId(styleId);
	}



}
