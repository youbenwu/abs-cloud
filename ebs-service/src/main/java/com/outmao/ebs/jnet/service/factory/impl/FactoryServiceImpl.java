package com.outmao.ebs.jnet.service.factory.impl;

import com.outmao.ebs.bbs.entity.Board;
import com.outmao.ebs.bbs.service.BbsService;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.jnet.domain.factory.FactoryDomain;
import com.outmao.ebs.jnet.dto.factory.FactoryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.IndustryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.ProductionCategoryParamsDTO;
import com.outmao.ebs.jnet.dto.factory.ProductionTechnologyParamsDTO;
import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.Industry;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.entity.factory.ProductionTechnology;
import com.outmao.ebs.jnet.service.factory.FactoryService;
import com.outmao.ebs.jnet.vo.factory.FactoryVO;
import com.outmao.ebs.jnet.vo.factory.IndustryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class FactoryServiceImpl extends BaseService implements FactoryService, CommandLineRunner {


    @Autowired
    FactoryDomain factoryDomain;

    @Autowired
    BbsService bbsService;


    @Override
    public void run(String... args) throws Exception {
        initData();
    }

    public void initData() {

        //在论坛系统里增加工厂板块
//        Board board=bbsService.getBoardByName("Factory");
//        if (board == null) {
//            BoardParamsDTO boardParamsDTO=new BoardParamsDTO();
//            boardParamsDTO.setName("Factory");
//            boardParamsDTO.setTitle("工厂板块");
//            bbsService.saveBoard(boardParamsDTO);
//        }

        //加点数据方便测试
        List<IndustryVO> industryVOS=getIndustryVOList();
        if(industryVOS.isEmpty()){

            Industry industry=this.saveIndustry(new IndustryParamsDTO(null,"服装"));

            //String[] names=new String[]{"梭织服装","针织服装","毛衫服装","皮革服装","裘皮服装"};
//            String[][] subNames=new String[][]{
//                    {"蕾丝/雪纺衫","衬衫","西服","夹克","风衣","休闲裤","牛仔","棉服","羽绒服","运动服","毛呢","童装","工作服","制服","校服","婚纱礼服","裙装","汉服","中老年服装","皮草","睡衣","背心/马甲","卫衣","旗袍","其他"},
//                    {"内衣","女士文胸","泳装","针织婴童装","针织运动/户外服","其它针织服装","休闲裤","衬衫","T恤/POLO衫","裙装","童装","睡衣","背心/马甲","皮草","毛呢","旗袍","卫衣","中老年服装"},
//                    {"毛衣","童装","其他"},
//                    {"皮衣","皮裤","仿皮"},
//                    {"裘皮","仿裘皮"},
//            };
            String[] names=new String[]{"梭织类","针织类","皮革类"};
            String[][] subNames=new String[][]{
                    {"蕾丝/雪纺衫","衬衫","西服","夹克","风衣","休闲裤","牛仔","棉服","羽绒服","运动服","毛呢","童装","工作服/制服/校服","婚纱礼服","裙装","汉服","中老年服装","皮草","睡衣","背心/马甲","卫衣","旗袍","其他"},
                    {"毛衣","内衣","女士文胸","泳装","运动/户外服","其他","休闲裤","衬衫","T恤/POLO衫","裙装","童装","睡衣","背心/马甲","皮草","毛呢","旗袍","卫衣","中老年服装"},
                    {"皮衣","皮裤"}
            };

            for (int i=0;i<names.length;i++){
                 ProductionCategory parent=this.saveProductionCategory(new ProductionCategoryParamsDTO(null,null,industry.getId(),names[i],null,0));
                for(String subName:subNames[i]){
                    this.saveProductionCategory(new ProductionCategoryParamsDTO(null,parent.getId(),industry.getId(),subName,config.getBaseUrl()+"/category/"+parent.getName()+"/"+subName.replace("/","&")+".jpg",0));
                }
            }

            String[] ptNames=new String[]{"织片","缝盘","后整"};
            String[] ptSubNames=new String[]{"3针","5针","7针","9针","12针","14针","16针","18针"};

            for(int t=0;t<ptNames.length;t++) {
                ProductionTechnology pt = this.saveProductionTechnology(new ProductionTechnologyParamsDTO(null, null, industry.getId(), ptNames[t], "台"));
                if (t == 0) {
                    for (String ptSubName : ptSubNames) {
                        this.saveProductionTechnology(new ProductionTechnologyParamsDTO(null, pt.getId(), industry.getId(), ptSubName, "台"));
                    }
                }
            }

        }

    }


    @Override
    public Factory saveFactory(FactoryParamsDTO params) {
        return factoryDomain.saveFactory(params);
    }

    @Override
    public Factory getFactoryById(Long id) {
        return factoryDomain.getFactoryById(id);
    }

    @Override
    public Factory getFactoryByUserId(Long userId) {
        return factoryDomain.getFactoryByUserId(userId);
    }

    @Override
    public Factory setFactoryStatus(Long id, int status, String statusRemark) {
        return factoryDomain.setFactoryStatus(id,status,statusRemark);
    }

    @Override
    public List<Factory> getFactoryListByUserIdIn(Collection<Long> userIds) {
        return factoryDomain.getFactoryListByUserIdIn(userIds);
    }

    @Override
    public FactoryVO getFactoryVOById(Long id) {
        return factoryDomain.getFactoryVOById(id);
    }

    @Override
    public FactoryVO getFactoryVOByUserId(Long userId) {
        return factoryDomain.getFactoryVOByUserId(userId);
    }

    @Override
    public Page<FactoryVO> getFactoryVOPageByStatus(Integer status, Pageable pageable) {
        if(status==null)
            return factoryDomain.getFactoryVOPage(pageable);
        return factoryDomain.getFactoryVOPageByStatus(status,pageable);
    }

    @Override
    public Industry saveIndustry(IndustryParamsDTO params) {
        return factoryDomain.saveIndustry(params);
    }

    @Override
    public void deleteIndustryById(Long id) {
        factoryDomain.deleteIndustryById(id);
    }

    @Override
    public Industry getIndustryById(Long id) {
        return factoryDomain.getIndustryById(id);
    }

    @Override
    public List<IndustryVO> getIndustryVOList() {
        return factoryDomain.getIndustryVOList();
    }

    @Override
    public List<IndustryVO> getIndustryVOAll() {
        return factoryDomain.getIndustryVOAll();
    }

    @Override
    public ProductionCategory saveProductionCategory(ProductionCategoryParamsDTO params) {
        return factoryDomain.saveProductionCategory(params);
    }

    @Override
    public void deleteProductionCategoryById(Long id) {
        factoryDomain.deleteProductionCategoryById(id);
    }

    @Override
    public List<ProductionCategory> getProductionCategoryListByIndustryId(Long industryId) {
        return factoryDomain.getProductionCategoryListByIndustryId(industryId);
    }

    @Override
    public ProductionTechnology saveProductionTechnology(ProductionTechnologyParamsDTO params) {
        return factoryDomain.saveProductionTechnology(params);
    }

    @Override
    public void deleteProductionTechnologyById(Long id) {
        factoryDomain.deleteProductionTechnologyById(id);
    }

    @Override
    public List<ProductionTechnology> getProductionTechnologyListByIndustryId(Long industryId) {
        return factoryDomain.getProductionTechnologyListByIndustryId(industryId);
    }

    @Override
    public Factory setFactoryTimeline(Long id, Date timeline) {
        return factoryDomain.setFactoryTimeline(id,timeline);
    }

    @Override
    public Date getFactoryTimeline(Long id) {
        return factoryDomain.getFactoryTimeline(id);
    }


    @Override
    public void dayAssignmentApplyNumSub(Long userId, int sub) {
        factoryDomain.dayAssignmentApplyNumSub(userId,sub);
    }

    @Override
    public int getDayAssignmentApplyNum(Long userId) {
        return factoryDomain.getDayAssignmentApplyNum(userId);
    }

    @Override
    public List<ProductionTechnology> getProductionTechnologyListByLevel(int level) {
        return factoryDomain.getProductionTechnologyListByLevel(level);
    }
}
