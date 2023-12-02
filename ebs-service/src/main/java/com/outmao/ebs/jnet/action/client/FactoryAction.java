package com.outmao.ebs.jnet.action.client;


import com.outmao.ebs.jnet.dto.factory.FactoryParamsDTO;
import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.ProductionTechnology;
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

import java.util.Date;
import java.util.List;

@Api(value = "/factory", tags = "工厂模块接口")
@RestController
@RequestMapping("/api/factory")
public class FactoryAction {

	@Autowired
    FactoryService service;

	/* 参数例子
{
  "contact": {
    "address": {
      "city": "广州",
      "details": "小新塘",
      "district": "天河区",
      "latitude": 0,
      "longitude": 0,
      "province": "广东",
      "zipcode": "510000"
    },
    "realname": "小强",
    "telphone": "15555555555",
    "visible": 0
  },
  "employees": 1500,
  "employeesText": "1000-2000人",
  "images": "dddddd",
  "intro": "工厂简价简价简价简价简价简价简价简价简价",
  "invoiceable": false,
  "minOrderQuantity": 10000,
  "name": "好工厂",
  "productionCategorys": [
    1589,1590,1591
  ],
  "productionTechnologys": [
    {
      "technologyId": 1645,
      "quantity": 5
    },
    {
      "technologyId": 1646,
      "quantity": 10
    },
   {
      "technologyId": 1653,
      "quantity": 20
    }
  ],
  "quoteSpeed": 100,
  "regCapital": "10万",
  "regTime": "2018",
  "type": 0,
  "userId": 1
}
	* */

	@PreAuthorize("principal.id.equals(#params.userId)")
	@ApiOperation(value = "保存工厂信息", notes = "保存工厂信息")
	@PostMapping(value = "/save")
	public FactoryVO saveFactory(@RequestBody FactoryParamsDTO params){
		Factory factory= service.saveFactory(params);
		return service.getFactoryVOById(factory.getId());
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取工厂信息", notes = "获取工厂信息")
	@PostMapping(value = "/get")
	public FactoryVO getFactoryVOById(Long id){
		return service.getFactoryVOById(id);
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取工厂信息", notes = "获取工厂信息")
	@PostMapping(value = "/getByUser")
	public FactoryVO getFactoryVOByUserId(Long userId){
		return service.getFactoryVOByUserId(userId);
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取工厂信息列表", notes = "获取工厂信息列表")
	@PostMapping(value = "/page")
	public Page<FactoryVO> getFactoryVOPage(Pageable pageable){
		return service.getFactoryVOPageByStatus(1,pageable);
	}

	/*
	 *
	 * 获取所有行业分类和分类下关联的生产工艺、生产品类
	 *
	 * */
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取所有行业分类和分类下关联的生产工艺、生产品类", notes = "获取所有行业分类和分类下关联的生产工艺、生产品类")
	@PostMapping(value = "/industry/all")
	public List<IndustryVO> getIndustryVOAll(){
		return service.getIndustryVOAll();
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取所有一级工艺", notes = "获取所有一级工艺")
	@PostMapping(value = "/technology/list")
	public List<ProductionTechnology> getProductionTechnologyListByLevel() {
		return service.getProductionTechnologyListByLevel(0);
	}


	@ApiOperation(value = "设置工厂档期", notes = "设置工厂档期格式yyyy-MM-dd HH:mm:ss")
	@PostMapping(value = "/setTimeline")
	public Factory setFactoryTimeline(Long id, Date timeline) {
		return service.setFactoryTimeline(id,timeline);
	}

	@ApiOperation(value = "获取工厂档期", notes = "获取工厂档期")
	@PostMapping(value = "/getTimeline")
	public Date getFactoryTimeline(Long id) {
		return service.getFactoryTimeline(id);
	}


	@ApiOperation(value = "获取当天可申请外发数量", notes = "获取当天可申请外发数量")
	@PostMapping(value = "/getDayAssignmentApplyNum")
	public int getDayAssignmentApplyNum(Long userId) {
		return service.getDayAssignmentApplyNum(userId);
	}


}
