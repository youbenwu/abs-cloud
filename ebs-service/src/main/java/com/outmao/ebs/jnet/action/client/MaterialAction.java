package com.outmao.ebs.jnet.action.client;


import com.outmao.ebs.jnet.dto.material.*;
import com.outmao.ebs.jnet.entity.material.MaterialPlan;
import com.outmao.ebs.jnet.entity.material.MaterialSupplier;
import com.outmao.ebs.jnet.entity.material.ProductionTemplate;
import com.outmao.ebs.jnet.entity.material.ProductionTemplateTechnology;
import com.outmao.ebs.jnet.service.material.MaterialService;
import com.outmao.ebs.jnet.vo.material.MaterialPlanVO;
import com.outmao.ebs.jnet.vo.material.MaterialVO;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateMaterialVO;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "/material", tags = "物料模块接口")
@RestController
@RequestMapping("/api/material")
public class MaterialAction {


    @Autowired
    MaterialService materialService;


    //MaterialSupplier
    @PreAuthorize("principal.id.equals(#params.userId)")
    @ApiOperation(value = "保存供货商信息", notes = "保存供货商信息")
    @PostMapping(value = "/supplier/save")
    public MaterialSupplier saveMaterialSupplier(@RequestBody MaterialSupplierParamsDTO params){
        return materialService.saveMaterialSupplier(params);
    }

    @ApiOperation(value = "删除供货商信息", notes = "删除供货商信息")
    @PostMapping(value = "/supplier/delete")
    public void deleteMaterialSupplierById(Long id){
        materialService.deleteMaterialSupplierById(id);
    }

    @ApiOperation(value = "获取供货商信息列表", notes = "获取供货商信息列表")
    @PostMapping(value = "/supplier/list")
    public List<MaterialSupplier> getMaterialSupplierListByUserId(Long userId){
        return materialService.getMaterialSupplierListByUserId(userId);
    }


    //Material
    @PreAuthorize("principal.id.equals(#params.userId)")
    @ApiOperation(value = "保存物料信息", notes = "保存物料信息")
    @PostMapping(value = "/save")
    public MaterialVO saveMaterial(@RequestBody MaterialParamsDTO params){
        return materialService.saveMaterial(params);
    }

    @ApiOperation(value = "删除物料信息", notes = "删除物料信息")
    @PostMapping(value = "/delete")
    public void deleteMaterialById(Long id){
        materialService.deleteMaterialById(id);
    }

    @ApiOperation(value = "获取物料信息列表", notes = "获取物料信息列表 type：0--物料 1--辅料，不传为全部； keyword：关键字筛选，不传为不筛选")
    @PostMapping(value = "/page")
    public Page<MaterialVO> getMaterialVOPageByUserId(Long userId, Integer type, String keyword,
                                                      @PageableDefault(page = 0, size = 10, sort = { "updateTime" }, direction = Sort.Direction.DESC)Pageable pageable){
        return materialService.getMaterialVOPageByUserId(userId,type,keyword,pageable);
    }


    //ProductionTemplate
    @ApiOperation(value = "保存样板", notes = "保存样板")
    @PostMapping(value = "/template/save")
    public ProductionTemplateVO saveProductionTemplate(@RequestBody ProductionTemplateParamsDTO params){
       ProductionTemplate template= materialService.saveProductionTemplate(params);
       return materialService.getProductionTemplateVOById(template.getId());
    }

    @ApiOperation(value = "获取样板", notes = "获取样板")
    @PostMapping(value = "/template/get")
    public ProductionTemplateVO getProductionTemplateVOById(Long id){
        return materialService.getProductionTemplateVOById(id);
    }

    @ApiOperation(value = "删除样板", notes = "删除样板")
    @PostMapping(value = "/template/delete")
    public void deleteProductionTemplateById(Long id){
        materialService.deleteProductionTemplateById(id);
    }


    @ApiOperation(value = "获取样板列表", notes = "获取样板列表")
    @PostMapping(value = "/template/page")
    public Page<ProductionTemplateVO> getProductionTemplatePageByUserId(
            Long userId,@PageableDefault(page = 0, size = 10, sort = { "updateTime" }, direction = Sort.Direction.DESC)Pageable pageable){
        return materialService.getProductionTemplateVOPageByUserId(userId,pageable);
    }

    @ApiOperation(value = "保存样板原料", notes = "保存样板原料")
    @PostMapping(value = "/template/material/save")
    public ProductionTemplateMaterialVO saveProductionTemplateMaterial(@RequestBody ProductionTemplateMaterialParamsDTO params){
        return materialService.saveProductionTemplateMaterial(params);
    }

    @ApiOperation(value = "删除样板原料", notes = "删除样板原料")
    @PostMapping(value = "/template/material/delete")
    public void deleteProductionTemplateMaterialById(Long id){
        materialService.deleteProductionTemplateMaterialById(id);
    }

    @ApiOperation(value = "获取样板原料列表", notes = "获取样板原料列表")
    @PostMapping(value = "/template/material/list")
    public List<ProductionTemplateMaterialVO> getProductionTemplateMaterialVOListByTemplateId(Long templateId){
        return materialService.getProductionTemplateMaterialVOListByTemplateId(templateId);
    }



    //MaterialPlan

    @ApiOperation(value = "保存物料计划", notes = "保存物料计划")
    @PostMapping(value = "/plan/save")
    public MaterialPlanVO saveMaterialPlan(@RequestBody MaterialPlanParamsDTO params){
       return materialService.saveMaterialPlan(params);
    }

    @ApiOperation(value = "删除物料计划", notes = "删除物料计划")
    @PostMapping(value = "/plan/delete")
    public void deleteMaterialPlanById(Long id){
        materialService.deleteMaterialPlanById(id);
    }

    @ApiOperation(value = "获取物料计划详情", notes = "获取物料计划详情")
    @PostMapping(value = "/plan/get")
    public MaterialPlanVO getMaterialPlanVOById(Long id){
        return materialService.getMaterialPlanVOById(id);
    }

    @ApiOperation(value = "获取物料计划列表", notes = "获取物料计划列表")
    @PostMapping(value = "/plan/page")
    public Page<MaterialPlan> getMaterialPlanPageByUserId(
            Long userId, @PageableDefault(page = 0, size = 10, sort = { "id" }, direction = Sort.Direction.DESC)Pageable pageable){
        return materialService.getMaterialPlanPageByUserId(userId,pageable);
    }


    @ApiOperation(value = "新增或修改样板工艺（有id修改，无id新增）", notes = "新增或修改样板工艺（有id修改，无id新增）")
    @PostMapping(value = "/template/technology/save")
    public Boolean saveProductionTemplateTech(ProductionTemplateTechnology dto){
        return materialService.saveProductionTemplateTech(dto);
    }

    @ApiOperation(value = "删除样板工艺", notes = "删除样板工艺")
    @PostMapping(value = "/template/technology/delete")
    public Boolean delProductionTemplateTech(Long id){
        return materialService.delProductionTemplateTech(id);
    }
}
