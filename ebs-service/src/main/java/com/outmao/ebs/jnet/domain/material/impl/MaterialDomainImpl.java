package com.outmao.ebs.jnet.domain.material.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.jnet.dao.factory.FactoryDao;
import com.outmao.ebs.jnet.dao.factory.ProductionCategoryDao;
import com.outmao.ebs.jnet.dao.material.*;
import com.outmao.ebs.jnet.domain.material.MaterialDomain;
import com.outmao.ebs.jnet.dto.material.*;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.entity.material.*;
import com.outmao.ebs.jnet.util.BigDecimalUtil;
import com.outmao.ebs.jnet.vo.material.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Transactional
@Component
public class MaterialDomainImpl extends BaseDomain implements MaterialDomain {

    @Autowired
    MaterialDao materialDao;
    @Autowired
    MaterialEntityDao materialEntityDao;
    @Autowired
    MaterialSupplierDao materialSupplierDao;
    @Autowired
    ProductionTemplateDao productionTemplateDao;
    @Autowired
    ProductionTemplateMaterialDao productionTemplateMaterialDao;

    @Autowired
    UserDao userDao;

    @Autowired
    FactoryDao factoryDao;
    @Autowired
    ProductionCategoryDao productionCategoryDao;
    @Autowired
    MaterialPlanDao materialPlanDao;
    @Autowired
    MaterialPlanMaterialDao materialPlanMaterialDao;

    @Autowired
    ProductionTemplateTechnologyDao productionTemplateTechnologyDao;

    @Override
    public Material saveMaterial(MaterialParamsDTO params) {
        Material material=params.getId()==null?null:materialDao.getOne(params.getId());
        if(material==null){
            material=new Material();
            material.setCreateTime(new Date());
            material.setUpdateTime(material.getCreateTime());
            material.setUser(userDao.getOne(params.getUserId()));
            material.setFactory(factoryDao.getOne(params.getFactoryId()));
        }else{
            material.setUpdateTime(new Date());
        }


        BeanUtils.copyProperties(params,material);

        if(params.getSupplierId()!=null){
            MaterialSupplier supplier=materialSupplierDao.findById(params.getSupplierId()).orElse(null);
            material.setSupplier(supplier);
        }else{
            material.setSupplier(null);
        }


        material=materialDao.save(material);


        //查找之前的物料实体
        List<MaterialEntity> entities=materialEntityDao.findAllByMaterial(material);

        //找出没被删除的ID列表
        List<Long> ids=new ArrayList<>();
        for(MaterialColorParamsDTO color :params.getColors()){
            if(color.getId()!=null){
                ids.add(color.getId());
            }
        }

        //找出被删除的实体
        List<MaterialEntity> dels=new ArrayList<>();
        for(MaterialEntity entity:entities){
            if(!ids.contains(entity.getId())){
                dels.add(entity);
            }
        }

        List<String> delnames=new ArrayList<>();
        for(MaterialEntity entity:dels){
            delnames.add(entity.getColor());
        }


        //增加新的实体
        List<MaterialEntity> news=new ArrayList<>();
        for(MaterialColorParamsDTO color :params.getColors()){
            MaterialEntity entity;
            if(color.getId()!=null){
                entity=materialEntityDao.getOne(color.getId());
                if(!entity.getMaterial().getId().equals(material.getId())){
                    throw new BusinessException("出错了");
                }
            }else{
                if(delnames.contains(color.getColor())){
                    entity=dels.get(delnames.indexOf(color.getColor()));
                    dels.remove(entity);
                    delnames.remove(color.getColor());
                }else{
                    entity=new MaterialEntity();
                    entity.setMaterial(material);
                    entity.setCreateTime(new Date());
                }
            }
            entity.setDeleted(false);
            entity.setVatNo(color.getVatNo());
            entity.setColor(color.getColor());
            entity.setImage(color.getImage());
            entity.setPrice(color.getPrice());

            news.add(entity);
        }

        materialEntityDao.saveAll(news);


        //删除不存在的
        for(MaterialEntity entity:dels){
            //只是设置状态，并不会真的删除
            if(!entity.isDeleted()) {
                entity.setDeleted(true);
            }
        }
        materialEntityDao.saveAll(dels);


        material.setEntitys(JsonUtil.toJson(news));
        material=materialDao.save(material);

        return material;
    }

    @Override
    public void updateMaterialEntitys(List<Long> list) {
        List<Material> materials=new ArrayList<>();
        for (Long id:list) {
            Material m=materialDao.getOne(id);
            List<MaterialEntity> entities=materialEntityDao.findAllByMaterialAndDeleted(m,false);
            m.setEntitys(JsonUtil.toJson(entities));
            materials.add(m);
        }
        materialDao.saveAll(materials);
    }

    @Override
    public Material getMaterialById(Long id) {
        return materialDao.findById(id).orElse(null);
    }

    @Override
    public void deleteMaterialById(Long id) {
        Material material=materialDao.getOne(id);
        material.setDeleted(true);
        materialDao.save(material);
    }

    @Override
    public Page<Material> getMaterialPageByUserId(Long userId, Pageable pageable) {
        return materialDao.findAllByUserAndDeletedFalse(userDao.getOne(userId),pageable);
    }


    @Override
    public Page<MaterialVO> getMaterialVOPageByUserId(Long userId,Integer type,String keyword, Pageable pageable) {
        QMaterial e=QMaterial.material;
        Predicate p=e.user.id.eq(userId).and(e.deleted.isFalse());
        if(type!=null){
            p=e.type.eq(type).and(p);
        }
        if(keyword!=null){
            p=e.name.like("%"+keyword+"%").and(p);
        }
        Page<Material> page=materialDao.findAll(p,pageable);
        return page.map(new Function<Material, MaterialVO>() {
            @Override
            public MaterialVO apply(Material material) {
                return new MaterialVO(material);
            }
        });
    }


    @Override
    public MaterialSupplier saveMaterialSupplier(MaterialSupplierParamsDTO params) {
        MaterialSupplier supplier=params.getId()==null?null:materialSupplierDao.getOne(params.getId());
        if(supplier==null){
            supplier=new MaterialSupplier();
            supplier.setCreateTime(new Date());
            supplier.setCreator(userDao.getOne(params.getUserId()));
        }
        BeanUtils.copyProperties(params,supplier);
        supplier=materialSupplierDao.save(supplier);
        return supplier;
    }

    @Override
    public void deleteMaterialSupplierById(Long id) {
        QMaterial e=QMaterial.material;
        QF.update(e).setNull(e.supplier).where(e.supplier.id.eq(id)).execute();
        materialSupplierDao.deleteById(id);
    }

    @Override
    public List<MaterialSupplier> getMaterialSupplierListByUserId(Long userId) {
        return materialSupplierDao.findAllByCreator(userDao.getOne(userId));
    }


    @Override
    @Transactional
    public ProductionTemplate saveProductionTemplate(ProductionTemplateParamsDTO params) {
        ProductionTemplate template=params.getId()==null?null:productionTemplateDao.getOne(params.getId());
        if(template==null){
            template=new ProductionTemplate();
            template.setUser(userDao.getOne(params.getUserId()));
            template.setFactory(factoryDao.getOne(params.getFactoryId()));
            template.setCreateTime(new Date());
            template.setUpdateTime(template.getCreateTime());
        }else{
            template.setUpdateTime(new Date());
            productionTemplateTechnologyDao.deleteByTemplateIds(Arrays.asList(params.getId()));
        }

        template.setCategory(productionCategoryDao.findById(params.getCategoryId()).orElse(null));
        BeanUtils.copyProperties(params,template);
        template=productionTemplateDao.save(template);

        SaveProductionTemplateTechDTO updateDto = new SaveProductionTemplateTechDTO();
        updateDto.setTemplateId(template.getId());
        updateDto.setTechnologies(params.getTechnologies());
        // 保存样版工艺
        this.saveProductionTemplateTechnologies(updateDto);

        return template;
    }

    @Override
    public ProductionTemplate getProductionTemplateById(Long id) {
        return productionTemplateDao.findById(id).orElse(null);
    }

    @Override
    public void deleteProductionTemplateById(Long id) {
        productionTemplateMaterialDao.deleteAllByTemplate(productionTemplateDao.getOne(id));
        productionTemplateDao.deleteById(id);
    }

    @Override
    public Page<ProductionTemplate> getProductionTemplatePageByUserId(Long userId,Pageable pageable) {
        return productionTemplateDao.findAllByUser(userDao.getOne(userId),pageable);
    }


    @Override
    public ProductionTemplateVO getProductionTemplateVOById(Long id) {
        QProductionTemplate e=QProductionTemplate.productionTemplate;
        Tuple tuple=QF.select(ProductionTemplateVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        if(tuple==null)
        return null;
        ProductionTemplateVO vo=new ProductionTemplateVO().fromTuple(tuple,e);
        ProductionCategory category=new ProductionCategory();
        BeanUtils.copyProperties(vo.getCategory(),category);
        vo.setCategory(category);
        List<ProductionTemplateMaterialVO> materialVOS=getProductionTemplateMaterialVOListByTemplateId(id);
        vo.setMaterials(materialVOS);

        try {
            List<ProductionTemplateTechnology> byTemplateIds = productionTemplateTechnologyDao.findByTemplateIds(Arrays.asList(id));
            if(CollectionUtils.isEmpty(byTemplateIds)){
                log.error("findByTemplateId templateId no data: "+id);
            }else{
                List<ProductionTemplateTechnologyDTO> technologies = new ArrayList<>(byTemplateIds.size());
                CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true); // 空值不拷贝
                for(ProductionTemplateTechnology t: byTemplateIds){
                    ProductionTemplateTechnologyDTO dto = new ProductionTemplateTechnologyDTO();
                    BeanUtil.copyProperties(t, dto, false, copyOptions);
                    dto.setCost(BigDecimalUtil.rounding4_5Cut0(t.getCost()));
                    technologies.add(dto);
                }

                vo.setTechnologies(technologies);
            }
        }catch (Exception e2){
            log.error("findByTemplateId err: ", e);
        }
        return vo;
    }

    @Override
    public Page<ProductionTemplateVO> getProductionTemplateVOPageByUserId(Long userId, Pageable pageable) {
        QProductionTemplate e=QProductionTemplate.productionTemplate;
        Page<ProductionTemplateVO> page=toPage(QF.select(ProductionTemplateVO.select(e)).from(e).where(e.user.id.eq(userId)),pageable,ProductionTemplateVO.class,e);
        return page;
    }

    @Override
    public ProductionTemplateMaterial saveProductionTemplateMaterial(ProductionTemplateMaterialParamsDTO params) {
        ProductionTemplateMaterial material=params.getId()==null?null:productionTemplateMaterialDao.getOne(params.getId());
        if(material==null){
            material=new ProductionTemplateMaterial();
            material.setTemplate(productionTemplateDao.getOne(params.getTemplateId()));
            material.setMaterial(materialEntityDao.getOne(params.getMaterialId()));
        }
        BeanUtils.copyProperties(params,material);
        material=productionTemplateMaterialDao.save(material);
        return material;
    }

    @Override
    public void deleteProductionTemplateMaterialById(Long id) {
        productionTemplateMaterialDao.deleteById(id);
    }

    @Override
    public List<ProductionTemplateMaterial> getProductionTemplateMaterialListByTemplateId(Long templateId) {
        return productionTemplateMaterialDao.findAllByTemplate(productionTemplateDao.getOne(templateId));
    }


    @Override
    public ProductionTemplateMaterialVO getProductionTemplateMaterialVOById(Long id) {
        QProductionTemplateMaterial e=QProductionTemplateMaterial.productionTemplateMaterial;
        Tuple tuple=QF.select(ProductionTemplateMaterialVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        return new ProductionTemplateMaterialVO().fromTuple(tuple,e);
    }

    @Override
    public List<ProductionTemplateMaterialVO> getProductionTemplateMaterialVOListByTemplateId(Long templateId) {
        QProductionTemplateMaterial e=QProductionTemplateMaterial.productionTemplateMaterial;
        return toList(QF.select(ProductionTemplateMaterialVO.select(e)).from(e).where(e.template.id.eq(templateId)),ProductionTemplateMaterialVO.class,e);
    }


    @Override
    public MaterialPlan saveMaterialPlan(MaterialPlanParamsDTO params) {

        MaterialPlan plan=params.getId()==null?null:materialPlanDao.getOne(params.getId());

        if(plan==null){
            plan=new MaterialPlan();
            plan.setUser(userDao.getOne(params.getUserId()));
            plan.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(params,plan);

        plan=materialPlanDao.save(plan);

        materialPlanMaterialDao.deleteAllByMaterialPlan(plan);

        if(params.getMaterials()!=null&&params.getMaterials().size()>0){

            List<MaterialPlanMaterial> materials=new ArrayList<>();

            for (MaterialPlanMaterialParamsDTO item : params.getMaterials()){
                MaterialPlanMaterial material=new MaterialPlanMaterial();
                material.setPlan(plan);
                material.setMaterial(materialEntityDao.getOne(item.getMaterialId()));
                BeanUtils.copyProperties(item,material);
                materials.add(material);
            }

            materialPlanMaterialDao.saveAll(materials);

        }

        return plan;
    }

    @Override
    public void deleteMaterialPlanById(Long id) {

        MaterialPlan plan=materialPlanDao.getOne(id);

        materialPlanMaterialDao.deleteAllByMaterialPlan(plan);

        materialPlanDao.delete(plan);

    }

    @Override
    public Page<MaterialPlan> getMaterialPlanPageByUserId(Long userId, Pageable pageable) {
        return materialPlanDao.findAllByUser(userDao.getOne(userId),pageable);
    }

    @Override
    public MaterialPlanVO getMaterialPlanVOById(Long id) {
        QMaterialPlan e=QMaterialPlan.materialPlan;
        Tuple tuple=QF.select(MaterialPlanVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        MaterialPlanVO vo=new MaterialPlanVO().fromTuple(tuple,e);
        List<MaterialPlanMaterialVO> materialVOS=getMaterialPlanMaterialVOListByMaterialPlanId(id);
        vo.setMaterials(materialVOS);
        return vo;
    }


    @Override
    public List<MaterialPlanMaterialVO> getMaterialPlanMaterialVOListByMaterialPlanId(Long planId) {
        QMaterialPlanMaterial e=QMaterialPlanMaterial.materialPlanMaterial;
        return toList(QF.select(MaterialPlanMaterialVO.select(e)).from(e).where(e.plan.id.eq(planId)),MaterialPlanMaterialVO.class,e);
    }

    /**
    * 批量保存样板工艺
    * @author yeyi
    * @date 2019/12/15 22:24
    **/
    private Boolean saveProductionTemplateTechnologies(SaveProductionTemplateTechDTO params) {
        // 保存样版工艺
        try {
            if(!CollectionUtils.isEmpty(params.getTechnologies())){
                return true;
            }

            final Date now = new Date();
            List<ProductionTemplateTechnology> technologies = new ArrayList<>(params.getTechnologies().size());
            CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true); // 空值不拷贝
            for(ProductionTemplateTechnologyDTO dto : params.getTechnologies()){
                ProductionTemplateTechnology vo = new ProductionTemplateTechnology();
                BeanUtil.copyProperties(dto, vo, false, copyOptions);
                vo.setCreateTime(now);
                vo.setIsDeleted((byte)0);
                vo.setId(null);
                vo.setTemplateId(dto.getTemplateId());
                vo.setCost(new BigDecimal(dto.getCost()));
                technologies.add(vo);
            }

            productionTemplateTechnologyDao.saveAll(technologies);
            return true;
        }catch (Exception e){
            log.error("saveProductionTemplateTec err: ", e);
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean saveProductionTemplateTech(ProductionTemplateTechnology params) {
        // 保存样版工艺
        try {
            ProductionTemplate template = this.getProductionTemplateById(params.getTemplateId());
            if(null==template){
                log.error("saveProductionTemplateTec templateId not exist: {}", params.getTemplateId());
                return false;
            }
            final Date now = new Date();
            final CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true); // 空值不拷贝
            ProductionTemplateTechnology vo = null;
            if(null==params.getId()){
                vo = new ProductionTemplateTechnology();
            }else{
                ProductionTemplateTechnology old = productionTemplateTechnologyDao.findById(params.getId()).orElse(null);
                if(null==old){
                    log.error("saveProductionTemplateTec id not exist: {}", params.getId());
                    return false;
                }
                if(old.getIsDeleted()==(byte)1){
                    log.error("saveProductionTemplateTec id deleted: {}", params.getId());
                    return false;
                }
                vo = old;
            }
            BeanUtil.copyProperties(params, vo, false, copyOptions);
            vo.setCreateTime(now);
            vo.setIsDeleted((byte)0);

            productionTemplateTechnologyDao.save(vo);
            return true;
        }catch (Exception e){
            log.error("saveProductionTemplateTec err: ", e);
            return false;
        }
    }

    @Override
    public Boolean delProductionTemplateTech(Long id) {
        ProductionTemplateTechnology old = productionTemplateTechnologyDao.findById(id).orElse(null);
        if(null==old){
            log.error("delProductionTemplateTec id not exist: {}", id);
            return false;
        }

        if((byte)1==old.getIsDeleted()){
            return true;
        }

        old.setIsDeleted((byte)1);
        productionTemplateTechnologyDao.save(old);
        return true;
    }

    @Override
    public List<MaterialEntityVO> getMaterialEntityVOListByIdIn(Collection<Long> ids) {
        QMaterialEntity e=QMaterialEntity.materialEntity;
        List<MaterialEntityVO> list= toList(QF.select(MaterialEntityVO.select(e)).from(e).where(e.id.in(ids)),MaterialEntityVO.class,e);

        Set<Long> supplierIds=new HashSet<>();
        for (MaterialEntityVO vo:list){
            if(vo.getSupplierId()!=null){
                supplierIds.add(vo.getSupplierId());
            }
        }

        if(supplierIds.size()>0) {
            List<MaterialSupplier> suppliers = materialSupplierDao.findAllByIdIn(supplierIds);
            Map<Long, MaterialSupplier> map = new HashMap<>();
            for (MaterialSupplier supplier : suppliers) {
                map.put(supplier.getId(), supplier);
            }
            for (MaterialEntityVO vo : list) {
                if (vo.getSupplierId() != null) {
                    vo.setSupplier(map.get(vo.getSupplierId()));
                }
            }
        }

        return list;
    }
}
