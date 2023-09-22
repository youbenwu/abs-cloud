package com.outmao.ebs.data.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.data.dao.CategoryDao;
import com.outmao.ebs.data.domain.CategoryDomain;
import com.outmao.ebs.data.domain.conver.CategoryVOConvert;
import com.outmao.ebs.data.domain.conver.SimpleCategoryVOConvert;
import com.outmao.ebs.data.dto.CategoryDTO;
import com.outmao.ebs.data.dto.GetCategoryListDTO;
import com.outmao.ebs.data.dto.SetCategoryStatusDTO;
import com.outmao.ebs.data.entity.Category;
import com.outmao.ebs.data.entity.QCategory;
import com.outmao.ebs.data.vo.CategoryVO;
import com.outmao.ebs.data.vo.SimpleCategoryVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class CategoryDomainImpl extends BaseDomain implements CategoryDomain {

    @Autowired
    private CategoryDao categoryDao;

    private CategoryVOConvert categoryVOConvert=new CategoryVOConvert();

    private SimpleCategoryVOConvert simpleCategoryVOConvert=new SimpleCategoryVOConvert();


    @Transactional
    @Override
    public Category saveCategory(CategoryDTO request) {
        Category category=request.getId()==null?null:categoryDao.findByIdForUpdate(request.getId());

        if(category==null){
            category=new Category();
            category.setType(request.getType());
            category.setTargetId(request.getTargetId());
            category.setCreateTime(new Date());
            category.setLeaf(true);
            if(request.getParentId()!=null){
                Category parent=categoryDao.findByIdForUpdate(request.getParentId());
                category.setParent(parent);
                category.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
        }

        category.setLetter(StringUtil.toPinyin(request.getTitle()));

        BeanUtils.copyProperties(request,category,"type","targetId");

        category.setUpdateTime(new Date());

        categoryDao.save(category);

        return category;
    }

    @Transactional
    @Override
    public Category setCategoryStatus(SetCategoryStatusDTO request) {
        Category category=categoryDao.getOne(request.getId());
        if(category==null){
            throw new BusinessException("分类不存在");
        }
        BeanUtils.copyProperties(request,category);
        categoryDao.save(category);
        return category;
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        Category category=categoryDao.getOne(id);
        if(category==null){
            throw new BusinessException("分类不存在");
        }
        if(!category.isLeaf()){
            throw new BusinessException("请先删除下级分类");
        }
        categoryDao.delete(category);
    }

    @Override
    public List<CategoryVO> getCategoryVOList(GetCategoryListDTO request) {
        QCategory e=QCategory.category;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getType())){
            p=e.type.eq(request.getType()).and(p);
        }

        if(request.getTargetId()!=null){
            p=e.targetId.eq(request.getTargetId()).and(p);
        }

        List<CategoryVO> list=queryList(e,e.level.eq(0).and(p),e.sort.asc(),categoryVOConvert);
        loadCategoryVOListChildren(list);
        return list;
    }

    private void loadCategoryVOListChildren(List<CategoryVO> list){
        QCategory e=QCategory.category;
        for(CategoryVO item:list){
            if(!item.isLeaf()){
                List<CategoryVO> children=queryList(e,e.parent.id.eq(item.getId()),e.sort.asc(),categoryVOConvert);
                loadCategoryVOListChildren(children);
                item.setChildren(children);
            }
        }
    }

    @Override
    public List<CategoryVO> getCategoryVOListByIdIn(Collection<Long> idIn) {
        QCategory e=QCategory.category;
        Predicate p=e.id.in(idIn);
        List<CategoryVO> list=queryList(e,p,categoryVOConvert);
        return list;
    }


    @Override
    public List<SimpleCategoryVO> getSimpleCategoryVOListByIdIn(Collection<Long> idIn) {
        QCategory e=QCategory.category;
        Predicate p=e.id.in(idIn);
        List<SimpleCategoryVO> list=queryList(e,p,simpleCategoryVOConvert);
        return list;
    }


    @Override
    public Page<CategoryVO> getCategoryVOPage(GetCategoryListDTO request, Pageable pageable) {

        QCategory e=QCategory.category;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getType())){
            p=e.type.eq(request.getType()).and(p);
        }

        if(request.getTargetId()!=null){
            p=e.targetId.eq(request.getTargetId()).and(p);
        }

        return queryPage(e,p,categoryVOConvert,pageable);
    }



}
