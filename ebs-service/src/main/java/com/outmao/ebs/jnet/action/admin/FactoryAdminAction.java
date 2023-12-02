package com.outmao.ebs.jnet.action.admin;



import com.outmao.ebs.jnet.dto.factory.FactoryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.IndustryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.ProductionCategoryParamsDTO;
import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.service.factory.FactoryService;
import com.outmao.ebs.jnet.vo.factory.FactoryVO;
import com.outmao.ebs.jnet.vo.factory.IndustryVO;
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


@Api(value = "/factory", tags = "后台--工厂模块接口")
@RestController
@RequestMapping("/api/admin/factory")
public class FactoryAdminAction {

	@Autowired
    FactoryService service;

	@PreAuthorize("hasPermission('**/factory/**','save')")
	@ApiOperation(value = "保存工厂信息", notes = "保存工厂信息")
	@PostMapping(value = "/save")
	public FactoryVO saveFactory(@RequestBody FactoryParamsDTO params){
		Factory factory= service.saveFactory(params);
		return service.getFactoryVOById(factory.getId());
	}

	@PreAuthorize("hasPermission('**/factory/**','save')")
	@ApiOperation(value = "审核工厂", notes = "审核工厂")
	@PostMapping(value = "/setStatus")
	public FactoryVO setFactoryStatus(Long id,int status,String statusRemark){
		Factory factory= service.setFactoryStatus(id,status,statusRemark);
		return service.getFactoryVOById(factory.getId());
	}

	@PreAuthorize("hasPermission('**/factory/**','read')")
	@ApiOperation(value = "获取工厂信息", notes = "获取工厂信息")
	@PostMapping(value = "/get")
	public FactoryVO getFactoryVOById(Long id){
		return service.getFactoryVOById(id);
	}

	@PreAuthorize("hasPermission('**/factory/**','read')")
	@ApiOperation(value = "获取工厂信息", notes = "获取工厂信息")
	@PostMapping(value = "/getByUser")
	public FactoryVO getFactoryVOByUserId(Long userId){
		return service.getFactoryVOByUserId(userId);
	}

	@PreAuthorize("hasPermission('**/factory/**','read')")
	@ApiOperation(value = "获取工厂信息列表", notes = "获取工厂信息列表")
	@PostMapping(value = "/page")
	public Page<FactoryVO> getFactoryVOPageByStatus(Integer status, Pageable pageable){
		return service.getFactoryVOPageByStatus(status,pageable);
	}


	//Industry相关

	@PreAuthorize("hasPermission('**/factory/industry/**','save')")
	@ApiOperation(value = "保存行业", notes = "保存行业")
	@PostMapping(value = "/industry/save")
	public void saveIndustry(IndustryParamsDTO params){
		service.saveIndustry(params);
	}

	@PreAuthorize("hasPermission('**/factory/industry/**','delete')")
	@ApiOperation(value = "删除行业", notes = "删除行业")
	@PostMapping(value = "/industry/delete")
	public void deleteIndustryById(Long id){
		service.deleteIndustryById(id);
	}

	@PreAuthorize("hasPermission('**/factory/industry/**','read')")
	@ApiOperation(value = "获取所有行业", notes = "获取所有行业")
	@PostMapping(value = "/industry/listBy")
	public List<IndustryVO> getIndustryVOList(){
		return service.getIndustryVOList();
	}



    //ProductionCategory相关
	/*
	 *
	 * 保存生产品类
	 *
	 * */
	@PreAuthorize("hasPermission('**/factory/category/**','save')")
	@ApiOperation(value = "保存生产品类", notes = "保存生产品类")
	@PostMapping(value = "/category/save")
	public ProductionCategory saveProductionCategory(@RequestBody ProductionCategoryParamsDTO params){
		return service.saveProductionCategory(params);
	}
	/*
	 *
	 * 删除生产品类
	 *
	 * */
	@PreAuthorize("hasPermission('**/factory/category/**','delete')")
	@ApiOperation(value = "删除生产品类", notes = "删除生产品类")
	@PostMapping(value = "/category/delete")
	public void deleteProductionCategoryById(Long id){
		service.deleteProductionCategoryById(id);
	}
	/*
	 *
	 * 获取行业下生产品类列表
	 *
	 * */
	@PreAuthorize("hasPermission('**/factory/category/**','read')")
	@ApiOperation(value = "获取生产品类", notes = "获取生产品类")
	@PostMapping(value = "/category/list")
	public List<ProductionCategory> getProductionCategoryListByIndustryId(Long industryId){
		return service.getProductionCategoryListByIndustryId(industryId);
	}





}
