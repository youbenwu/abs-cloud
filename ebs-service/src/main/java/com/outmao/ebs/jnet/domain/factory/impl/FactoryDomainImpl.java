package com.outmao.ebs.jnet.domain.factory.impl;

import com.outmao.ebs.bbs.dto.subject.SubjectDTO;
import com.outmao.ebs.bbs.entity.Board;
import com.outmao.ebs.bbs.entity.Subject;
import com.outmao.ebs.bbs.service.BbsService;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.vo.Address;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.dto.UserDetailsDTO;
import com.outmao.ebs.user.entity.UserDetails;
import com.outmao.ebs.jnet.dao.factory.*;
import com.outmao.ebs.jnet.domain.factory.FactoryDomain;
import com.outmao.ebs.jnet.dto.factory.*;
import com.outmao.ebs.jnet.entity.factory.*;
import com.outmao.ebs.jnet.vo.factory.FactoryVO;
import com.outmao.ebs.jnet.vo.factory.IndustryVO;
import com.outmao.ebs.user.service.UserService;
import com.querydsl.core.Tuple;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class FactoryDomainImpl extends BaseDomain implements FactoryDomain {

    @Autowired
    FactoryDao factoryDao;

    @Autowired
    IndustryDao industryDao;

    @Autowired
    FactoryProductionCategoryDao factoryProductionCategoryDao;

    @Autowired
    FactoryProductionTechnologyDao factoryProductionTechnologyDao;

    @Autowired
    ProductionCategoryDao productionCategoryDao;

    @Autowired
    ProductionTechnologyDao productionTechnologyDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;


    @Autowired
    BbsService bbsService;

    @Override
    public Factory saveFactory(FactoryParamsDTO params) {


        Factory factory=params.getId()==null?null:factoryDao.getOne(params.getId());
        if(factory==null){
            factory=new Factory();
            factory.setCreateTime(new Date());
            factory.setUser(userDao.getOne(params.getUserId()));
        }
        factory.setUpdateTime(new Date());
        BeanUtils.copyProperties(params,factory);

        //不用审核直接设置正常状态
        factory.setStatus(1);

        factory=factoryDao.save(factory);

        //有推广者
        if(params.getId()==null&&factory.getInviterId()!=null){
            Factory inviter=factoryDao.findByUser(userDao.getOne(factory.getInviterId()));
            if(inviter!=null){
                inviter.setInvites(inviter.getInvites()+1);
                inviter.setDayMaxAssignmentApplyNum(inviter.getInvites()+3);
                inviter.setDayAssignmentApplyNum(inviter.getDayAssignmentApplyNum()+1);
                factoryDao.save(inviter);
            }
        }


        //接入论坛
        if(factory.getSubject()==null){
            Item item=factory.toItem();
            BindingItem bindingItem=new BindingItem(item.getId(),item.getType(),item.getTitle());

            //Board board=bbsService.getBoardByType(item.getType());
            SubjectDTO subjectParamsDTO=new SubjectDTO();
            subjectParamsDTO.setUserId(factory.getUser().getId());
            //subjectParamsDTO.setBoardId(board.getId());
            subjectParamsDTO.setTitle(item.getTitle());
            //subjectParamsDTO.setSubtitle(item.getSubtitle());
            //subjectParamsDTO.setImage(item.getImage());
            subjectParamsDTO.setItem(bindingItem);
            Subject subject=bbsService.saveSubject(subjectParamsDTO);
            factory.setSubject(subject);
            factory=factoryDao.save(factory);
        }

        //工艺
        factoryProductionTechnologyDao.deleteAllByFactory(factory);
        if(params.getProductionTechnologys()!=null){
            for(FactoryProductionTechnologyParamsDTO item:params.getProductionTechnologys()){
               this.saveFactoryProductionTechnology(factory,productionTechnologyDao.getOne(item.getTechnologyId()),item.getQuantity());
            }
        }

        //品类

        factoryProductionCategoryDao.deleteAllByFactory(factory);
        if(params.getProductionCategorys()!=null){
            for(Long cid:params.getProductionCategorys()){
                this.saveFactoryProductionCategory(factory,productionCategoryDao.getOne(cid));
            }
        }

        //更新用户信息
        if(factory.getContact()!=null) {
            UserDetails userDetails = factory.getUser().getDetails();
            UserDetailsDTO paramsDTO = new UserDetailsDTO();
            paramsDTO.setId(userDetails.getUser().getId());
            if (userDetails.getPhone() == null ){
                paramsDTO.setPhone(factory.getContact().getPhone());
            }
            if(userDetails.getRealName()==null){
                paramsDTO.setRealName(factory.getContact().getName());
            }
            if(factory.getContact().getAddress()!=null){
                Address address=factory.getContact().getAddress();
                userDetails.setAddress(address.toString());
            }
            userService.modifyUserDetails(paramsDTO);
        }

        return factory;
    }

    @Override
    public void dayAssignmentApplyNumSub(Long userId, int sub) {
        Factory inviter=factoryDao.findByUser(userDao.getOne(userId));
        if(inviter!=null){
            inviter.setDayAssignmentApplyNum(inviter.getDayAssignmentApplyNum()-sub);
            factoryDao.save(inviter);
        }
    }

    @Override
    public int getDayAssignmentApplyNum(Long userId) {
        Factory factory=factoryDao.findByUser(userDao.getOne(userId));

        if(factory==null)
            return 0;

        Date now=new Date();

        Date time=factory.getDayAssignmentApplyNumUpdateTime();

        if(time==null||!DateUtil.isSameDay(now,time)){
            if(factory.getDayMaxAssignmentApplyNum()==0){
                factory.setDayMaxAssignmentApplyNum(3);
            }
            factory.setDayAssignmentApplyNum(factory.getDayMaxAssignmentApplyNum());
            factory.setDayAssignmentApplyNumUpdateTime(now);
            factoryDao.save(factory);
        }

        return factory.getDayAssignmentApplyNum();
    }

    @Override
    public Factory getFactoryById(Long id) {
        return factoryDao.findById(id).orElse(null);
    }

    @Override
    public Factory getFactoryByUserId(Long userId) {
        return factoryDao.findByUser(userDao.getOne(userId));
    }

    @Override
    public Factory setFactoryStatus(Long id, int status, String statusRemark) {
        Factory factory=factoryDao.getOne(id);
        factory.setStatus(status);
        factory.setStatusRemark(statusRemark);
        factory=factoryDao.save(factory);
        return factory;
    }

    @Override
    public Factory setFactoryTimeline(Long id, Date timeline) {
        Factory factory=factoryDao.getOne(id);
        factory.setTimeline(timeline);
        factory.setUpdateTime(new Date());
        factory=factoryDao.save(factory);
        return factory;
    }

    @Override
    public Date getFactoryTimeline(Long id) {
        Factory factory=factoryDao.getOne(id);
        return factory.getTimeline();
    }

    @Override
    public FactoryVO getFactoryVOById(Long id) {
        QFactory e=QFactory.factory;
        Tuple t=QF.select(FactoryVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        if(t==null){
            throw new BusinessException("无此工厂");
        }
        FactoryVO vo=new FactoryVO().fromTuple(t,e);
        vo.setProductionCategorys(factoryProductionCategoryDao.findAllByFactory(factoryDao.getOne(vo.getId())));
        vo.setProductionTechnologys(factoryProductionTechnologyDao.findAllByFactory(factoryDao.getOne(vo.getId())));
        return vo;
    }


    @Override
    public List<Factory> getFactoryListByUserIdIn(Collection<Long> userIds) {
        return factoryDao.findAllByUserIdIn(userIds);
    }

    @Override
    public FactoryVO getFactoryVOByUserId(Long userId) {
        QFactory e=QFactory.factory;
        Tuple t=QF.select(FactoryVO.select(e)).from(e).where(e.user.id.eq(userId)).fetchOne();
        if(t==null)
            return null;
        FactoryVO vo=new FactoryVO().fromTuple(t,e);
        vo.setProductionCategorys(factoryProductionCategoryDao.findAllByFactory(factoryDao.getOne(vo.getId())));
        vo.setProductionTechnologys(factoryProductionTechnologyDao.findAllByFactory(factoryDao.getOne(vo.getId())));
        return vo;
    }

    @Override
    public Page<FactoryVO> getFactoryVOPage(Pageable pageable) {
        QFactory e=QFactory.factory;
        Page<FactoryVO> page=toPage(QF.select(FactoryVO.select(e)).from(e),pageable,FactoryVO.class,e);
        return page;
    }

    @Override
    public Page<FactoryVO> getFactoryVOPageByStatus(int status, Pageable pageable) {
        QFactory e=QFactory.factory;
        Page<FactoryVO> page=toPage(QF.select(FactoryVO.select(e)).from(e).where(e.status.eq(status)),pageable,FactoryVO.class,e);
        return page;
    }


    @Override
    public FactoryProductionTechnology saveFactoryProductionTechnology(Factory factory, ProductionTechnology productionTechnology, int quantity) {
        FactoryProductionTechnology t=new FactoryProductionTechnology();
        t.setFactory(factory);
        t.setTechnology(productionTechnology);
        t.setQuantity(quantity);
        t.setCreateTime(new Date());
        t.setTechnologyId(productionTechnology.getId());
        t.setTechnologyName(productionTechnology.getName());
        t.setTechnologySuffix(productionTechnology.getSuffix());
        if(productionTechnology.getParent()!=null){
            t.setParentTechnologyName(productionTechnology.getParent().getName());
        }
        t=factoryProductionTechnologyDao.save(t);
        return t;
    }

    @Override
    public FactoryProductionCategory saveFactoryProductionCategory(Factory factory, ProductionCategory productionCategory) {
        FactoryProductionCategory c=new FactoryProductionCategory();
        c.setFactory(factory);
        c.setCategory(productionCategory);
        c.setCreateTime(new Date());
        c.setCategoryId(productionCategory.getId());
        c.setCategoryName(productionCategory.getName());
        c.setCategoryImage(productionCategory.getImage());
        if(productionCategory.getParent()!=null){
            c.setParentCategoryName(productionCategory.getParent().getName());
        }
        c=factoryProductionCategoryDao.save(c);
        return  c;
    }

    @CacheEvict(value = "cache_industrys", allEntries = true)
    @Override
    public Industry saveIndustry(IndustryParamsDTO params) {
        Industry industry;
        if(params.getId()==null){
            industry=new Industry();
            industry.setCreateTime(new Date());
            industry.setUpdateTime(industry.getCreateTime());
//            industry.setLeaf(true);
//            if(params.getParentId()!=null){
//                Industry parent=industryDao.getOne(params.getParentId());
//                industry.setParent(parent);
//                industry.setLevel(parent.getLevel()+1);
//                if(parent.isLeaf()){
//                    parent.setLeaf(false);
//                    industryDao.save(parent);
//                }
//            }
        }else{
            industry=industryDao.getOne(params.getId());
            industry.setUpdateTime(new Date());
        }
        industry.setName(params.getName());
        industry=industryDao.save(industry);
        return industry;
    }

    @Override
    public void deleteIndustryById(Long id) {
        industryDao.deleteById(id);
    }

    @Override
    public Industry getIndustryById(Long id) {
        return industryDao.findById(id).orElse(null);
    }


    @Cacheable(value = "cache_industrys", key = "method.name")
    @Override
    public List<IndustryVO> getIndustryVOList() {
        //取一级分类
        List<Industry> list=industryDao.findAll();

        List<IndustryVO> vos=new ArrayList<>(list.size());

        for (Industry sub : list){
            IndustryVO vo=new IndustryVO(sub);
            vos.add(vo);
        }

        return vos;
    }

    @Cacheable(value = "cache_industrys", key = "method.name")
    @Override
    public List<IndustryVO> getIndustryVOAll() {
        //取一级分类
        List<Industry> list=industryDao.findAll();

        List<IndustryVO> vos=new ArrayList<>(list.size());

        for (Industry sub : list){
            IndustryVO vo=new IndustryVO(sub);
            List<ProductionTechnology> productionTechnologies=getProductionTechnologyListByIndustryId(sub.getId());
            List<ProductionCategory> productionCategories=getProductionCategoryListByIndustryId(sub.getId());
            vo.setProductionTechnologies(productionTechnologies);
            vo.setProductionCategories(productionCategories);
            vos.add(vo);
        }

        return vos;
    }

    @CacheEvict(value = "cache_industrys", allEntries = true)
    @Override
    public ProductionCategory saveProductionCategory(ProductionCategoryParamsDTO params) {
        ProductionCategory c=params.getId()==null?null:productionCategoryDao.getOne(params.getId());
        if(c==null){
            c=new ProductionCategory();
            c.setCreateTime(new Date());
            if(params.getIndustryId()!=null) {
                Industry industry = industryDao.getOne(params.getIndustryId());
                c.setIndustry(industry);
            }
            c.setLeaf(true);
            if(params.getParentId()!=null){
                ProductionCategory parent=productionCategoryDao.getOne(params.getParentId());
                c.setParent(parent);
                c.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                    productionCategoryDao.save(parent);
                }
            }
        }
        c.setName(params.getName());
        c.setImage(params.getImage());
        c.setSort(params.getSort());
        c=productionCategoryDao.save(c);
        return c;
    }

    @Override
    public void deleteProductionCategoryById(Long id) {
        productionCategoryDao.deleteById(id);
    }

    @Override
    public List<ProductionCategory> getProductionCategoryListByIndustryId(Long industryId) {
        return productionCategoryDao.findAllByIndustryAndLevelOrderBySortDesc(industryDao.getOne(industryId),0);
    }


    @CacheEvict(value = "cache_industrys", allEntries = true)
    @Override
    public ProductionTechnology saveProductionTechnology(ProductionTechnologyParamsDTO params) {
        ProductionTechnology t=params.getId()==null?null:productionTechnologyDao.getOne(params.getId());
        if(t==null){
            t=new ProductionTechnology();
            t.setCreateTime(new Date());
            t.setLeaf(true);
            if(params.getIndustryId()!=null) {
                Industry industry = industryDao.getOne(params.getIndustryId());
                t.setIndustry(industry);
            }
            if(params.getParentId()!=null){
                ProductionTechnology parent=productionTechnologyDao.getOne(params.getParentId());
                t.setParent(parent);
                t.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                    productionTechnologyDao.save(parent);
                }
            }
        }
        t.setName(params.getName());
        t.setSuffix(params.getSuffix());
        t=productionTechnologyDao.save(t);
        return t;
    }

    @Override
    public void deleteProductionTechnologyById(Long id) {
        productionTechnologyDao.deleteById(id);
    }

    @Override
    public List<ProductionTechnology> getProductionTechnologyListByIndustryId(Long industryId) {
        return productionTechnologyDao.findAllByIndustryAndLevel(industryDao.getOne(industryId),0);
    }


    @Override
    public List<ProductionTechnology> getProductionTechnologyListByLevel(int level) {
        return productionTechnologyDao.findAllByLevel(level);
    }
}
