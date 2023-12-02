package com.outmao.ebs.jnet.service.material.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.jnet.domain.material.MaterialDomain;
import com.outmao.ebs.jnet.dto.material.*;
import com.outmao.ebs.jnet.entity.material.*;
import com.outmao.ebs.jnet.service.material.MaterialService;
import com.outmao.ebs.jnet.vo.material.MaterialPlanVO;
import com.outmao.ebs.jnet.vo.material.MaterialVO;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateMaterialVO;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
public class MaterialServiceImpl extends BaseService implements MaterialService {


    @Autowired
    MaterialDomain materialDomain;


    @Override
    public MaterialSupplier saveMaterialSupplier(MaterialSupplierParamsDTO params) {
        return materialDomain.saveMaterialSupplier(params);
    }

    @Override
    public void deleteMaterialSupplierById(Long id) {

        materialDomain.deleteMaterialSupplierById(id);
    }

    @Override
    public List<MaterialSupplier> getMaterialSupplierListByUserId(Long userId) {
        return materialDomain.getMaterialSupplierListByUserId(userId);
    }

    @Override
    public MaterialVO saveMaterial(MaterialParamsDTO params) {
        Material material= materialDomain.saveMaterial(params);
        return new MaterialVO(material);
    }

    @Override
    public MaterialVO getMaterialById(Long id) {
        Material material= materialDomain.getMaterialById(id);
        return new MaterialVO(material);
    }

    @Override
    public void deleteMaterialById(Long id) {
        materialDomain.deleteMaterialById(id);
    }

    @Override
    public Page<MaterialVO> getMaterialVOPageByUserId(Long userId,Integer type,String keyword, Pageable pageable) {
        return materialDomain.getMaterialVOPageByUserId(userId,type,keyword,pageable);
    }


    @Override
    public ProductionTemplate saveProductionTemplate(ProductionTemplateParamsDTO params) {
        ProductionTemplate template= materialDomain.saveProductionTemplate(params);
        return template;
    }

    @Override
    public ProductionTemplateVO getProductionTemplateVOById(Long id) {
        return materialDomain.getProductionTemplateVOById(id);
    }

    @Override
    public void deleteProductionTemplateById(Long id) {
        materialDomain.deleteProductionTemplateById(id);
    }

    @Override
    public Page<ProductionTemplateVO> getProductionTemplateVOPageByUserId(Long userId, Pageable pageable) {
        return materialDomain.getProductionTemplateVOPageByUserId(userId,pageable);
    }

    @Override
    public ProductionTemplateMaterialVO saveProductionTemplateMaterial(ProductionTemplateMaterialParamsDTO params) {
        ProductionTemplateMaterial material=materialDomain.saveProductionTemplateMaterial(params);
        return materialDomain.getProductionTemplateMaterialVOById(material.getId());
    }

    @Override
    public void deleteProductionTemplateMaterialById(Long id) {
        materialDomain.deleteProductionTemplateMaterialById(id);
    }

    @Override
    public List<ProductionTemplateMaterialVO> getProductionTemplateMaterialVOListByTemplateId(Long templateId) {
        return materialDomain.getProductionTemplateMaterialVOListByTemplateId(templateId);
    }


    @Override
    public MaterialPlanVO saveMaterialPlan(MaterialPlanParamsDTO params) {
        MaterialPlan plan=materialDomain.saveMaterialPlan(params);
        return materialDomain.getMaterialPlanVOById(plan.getId());
    }

    @Override
    public void deleteMaterialPlanById(Long id) {
        materialDomain.deleteMaterialPlanById(id);
    }

    @Override
    public MaterialPlanVO getMaterialPlanVOById(Long id) {
        return materialDomain.getMaterialPlanVOById(id);
    }

    @Override
    public Page<MaterialPlan> getMaterialPlanPageByUserId(Long userId, Pageable pageable) {
        return materialDomain.getMaterialPlanPageByUserId(userId,pageable);
    }

    @Override
    public Boolean saveProductionTemplateTech(ProductionTemplateTechnology dto) {
        ProductionTemplate tem = materialDomain.getProductionTemplateById(dto.getTemplateId());
        if(null==tem){
            log.error("saveProductionTemplateTec templateId not exist: {}", dto.getTemplateId());
            return false;
        }
        return materialDomain.saveProductionTemplateTech(dto);
    }

    @Override
    public Boolean delProductionTemplateTech(Long id) {
        return materialDomain.delProductionTemplateTech(id);
    }
}
