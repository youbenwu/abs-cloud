package com.outmao.ebs.jnet.service.material;

import com.outmao.ebs.jnet.dto.material.*;
import com.outmao.ebs.jnet.entity.material.MaterialPlan;
import com.outmao.ebs.jnet.entity.material.MaterialSupplier;
import com.outmao.ebs.jnet.entity.material.ProductionTemplate;
import com.outmao.ebs.jnet.entity.material.ProductionTemplateTechnology;
import com.outmao.ebs.jnet.vo.material.MaterialPlanVO;
import com.outmao.ebs.jnet.vo.material.MaterialVO;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateMaterialVO;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaterialService {


    //MaterialSupplier
    public MaterialSupplier saveMaterialSupplier(MaterialSupplierParamsDTO params);
    public void deleteMaterialSupplierById(Long id);
    public List<MaterialSupplier> getMaterialSupplierListByUserId(Long userId);

    //Material
    public MaterialVO saveMaterial(MaterialParamsDTO params);
    public MaterialVO getMaterialById(Long id);
    public void deleteMaterialById(Long id);
    public Page<MaterialVO> getMaterialVOPageByUserId(Long userId, Integer type, String keyword, Pageable pageable);


    //ProductionTemplate

    public ProductionTemplate saveProductionTemplate(ProductionTemplateParamsDTO params);
    public ProductionTemplateVO getProductionTemplateVOById(Long id);
    public void deleteProductionTemplateById(Long id);
    public Page<ProductionTemplateVO> getProductionTemplateVOPageByUserId(Long userId, Pageable pageable);


    public ProductionTemplateMaterialVO saveProductionTemplateMaterial(ProductionTemplateMaterialParamsDTO params);
    public void deleteProductionTemplateMaterialById(Long id);
    public List<ProductionTemplateMaterialVO> getProductionTemplateMaterialVOListByTemplateId(Long templateId);


    //MaterialPlan

    public MaterialPlanVO saveMaterialPlan(MaterialPlanParamsDTO params);

    public void deleteMaterialPlanById(Long id);

    public MaterialPlanVO getMaterialPlanVOById(Long id);


    public Page<MaterialPlan> getMaterialPlanPageByUserId(Long userId, Pageable pageable);


    /**
    * 更新样版工艺
    * @author yeyi
    * @date 2019/12/15 17:46
    **/
    public Boolean saveProductionTemplateTech(ProductionTemplateTechnology dto);

    public Boolean delProductionTemplateTech(Long id);
}
