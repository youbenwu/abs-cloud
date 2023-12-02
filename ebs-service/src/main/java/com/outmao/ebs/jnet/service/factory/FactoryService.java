package com.outmao.ebs.jnet.service.factory;

import com.outmao.ebs.jnet.dto.factory.FactoryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.IndustryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.ProductionCategoryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.ProductionTechnologyParamsDTO;
import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.Industry;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.entity.factory.ProductionTechnology;
import com.outmao.ebs.jnet.vo.factory.FactoryVO;
import com.outmao.ebs.jnet.vo.factory.IndustryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface FactoryService {


    public Factory saveFactory(FactoryParamsDTO params);
    public Factory getFactoryById(Long id);
    public Factory getFactoryByUserId(Long userId);
    public Factory setFactoryStatus(Long id, int status, String statusRemark);

    public Factory setFactoryTimeline(Long id, Date timeline);
    public Date getFactoryTimeline(Long id);

    public List<Factory> getFactoryListByUserIdIn(Collection<Long> userIds);

    public FactoryVO getFactoryVOById(Long id);
    public FactoryVO getFactoryVOByUserId(Long userId);
    public Page<FactoryVO> getFactoryVOPageByStatus(Integer status, Pageable pageable);

    public void dayAssignmentApplyNumSub(Long userId, int sub);

    public int getDayAssignmentApplyNum(Long userId);


    //Industry相关

    /*
     *
     * 保存行业分类
     * */
    public Industry saveIndustry(IndustryParamsDTO params);
    /*
     *
     * 删除行业分类
     * */
    public void deleteIndustryById(Long id);
    /*
     *
     * 获取行业分类
     * */
    public Industry getIndustryById(Long id);
    /*
     *
     * 获取所有行业分级
     * */
    public List<IndustryVO> getIndustryVOList();


    /*
     *
     * 获取所有行业分类和分类下关联的生产工艺、生产品类
     *
     * */
    public List<IndustryVO> getIndustryVOAll();



    //ProductionCategory相关
    /*
     *
     * 保存生产品类
     *
     * */
    public ProductionCategory saveProductionCategory(ProductionCategoryParamsDTO params);
    /*
     *
     * 删除生产品类
     *
     * */
    public void deleteProductionCategoryById(Long id);
    /*
     *
     * 获取行业下生产品类列表
     *
     * */
    public List<ProductionCategory> getProductionCategoryListByIndustryId(Long industryId);


    //ProductionTechnology相关

    /*
     *
     * 保存生产工艺
     *
     * */
    public ProductionTechnology saveProductionTechnology(ProductionTechnologyParamsDTO params);
    /*
     *
     * 删除生产工艺
     *
     * */
    public void deleteProductionTechnologyById(Long id);
    /*
     *
     * 获取行业下生产工艺列表
     *
     * */
    public List<ProductionTechnology> getProductionTechnologyListByIndustryId(Long industryId);

    public List<ProductionTechnology> getProductionTechnologyListByLevel(int level);





}
