package com.outmao.ebs.jnet.domain.material;

import com.outmao.ebs.jnet.dto.material.*;
import com.outmao.ebs.jnet.entity.material.*;
import com.outmao.ebs.jnet.vo.material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface MaterialDomain {



    //MaterialSupplier
    public MaterialSupplier saveMaterialSupplier(MaterialSupplierParamsDTO params);
    public void deleteMaterialSupplierById(Long id);
    public List<MaterialSupplier> getMaterialSupplierListByUserId(Long userId);

    //Material
    public Material saveMaterial(MaterialParamsDTO params);
    public Material getMaterialById(Long id);
    public void deleteMaterialById(Long id);
    public Page<Material> getMaterialPageByUserId(Long userId, Pageable pageable);
    public Page<MaterialVO> getMaterialVOPageByUserId(Long userId, Integer type, String keyword, Pageable pageable);

    public void updateMaterialEntitys(List<Long> list);


    public List<MaterialEntityVO> getMaterialEntityVOListByIdIn(Collection<Long> ids);

    //ProductionTemplate

    public ProductionTemplate saveProductionTemplate(ProductionTemplateParamsDTO params);
    public ProductionTemplate getProductionTemplateById(Long id);
    public void deleteProductionTemplateById(Long id);
    public Page<ProductionTemplate> getProductionTemplatePageByUserId(Long userId, Pageable pageable);

    public ProductionTemplateVO getProductionTemplateVOById(Long id);
    public Page<ProductionTemplateVO> getProductionTemplateVOPageByUserId(Long userId, Pageable pageable);


    public ProductionTemplateMaterial saveProductionTemplateMaterial(ProductionTemplateMaterialParamsDTO params);
    public void deleteProductionTemplateMaterialById(Long id);
    public List<ProductionTemplateMaterial> getProductionTemplateMaterialListByTemplateId(Long templateId);

    public ProductionTemplateMaterialVO getProductionTemplateMaterialVOById(Long id);
    public List<ProductionTemplateMaterialVO> getProductionTemplateMaterialVOListByTemplateId(Long templateId);


    //MaterialPlan

    public MaterialPlan saveMaterialPlan(MaterialPlanParamsDTO params);
    public void deleteMaterialPlanById(Long id);

    public MaterialPlanVO getMaterialPlanVOById(Long id);


    public Page<MaterialPlan> getMaterialPlanPageByUserId(Long userId, Pageable pageable);


    public List<MaterialPlanMaterialVO> getMaterialPlanMaterialVOListByMaterialPlanId(Long planId);

    public Boolean saveProductionTemplateTech(ProductionTemplateTechnology dto);

    public Boolean delProductionTemplateTech(Long id);
}
