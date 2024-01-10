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
import java.util.*;
import java.util.stream.Collectors;


@Component
public class CategoryDomainImpl extends BaseDomain implements CategoryDomain {

    @Autowired
    private CategoryDao categoryDao;

    private CategoryVOConvert categoryVOConvert=new CategoryVOConvert();

    private SimpleCategoryVOConvert simpleCategoryVOConvert=new SimpleCategoryVOConvert();


    @Transactional
    @Override
    public Category saveCategory(CategoryDTO request) {
        Category category=request.getId()==null?null:categoryDao.findByIdLock(request.getId());

        if(category==null){
            category=new Category();
            category.setOrgId(request.getOrgId());
            category.setType(request.getType());
            category.setTargetId(request.getTargetId());
            category.setCreateTime(new Date());
            category.setLeaf(true);
            if(request.getParentId()!=null){
                Category parent=categoryDao.findByIdLock(request.getParentId());
                category.setParent(parent);
                category.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
        }

        category.setLetter(StringUtil.toPinyin(request.getTitle()));

        BeanUtils.copyProperties(request,category,"orgId","type","targetId");

        category.setUpdateTime(new Date());

        categoryDao.save(category);

        return category;
    }

    @Transactional
    @Override
    public Category setCategoryStatus(SetCategoryStatusDTO request) {
        Category category=categoryDao.findByIdLock(request.getId());
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
        if(category.getParent()!=null&&category.getParent().getChildren().size()==1){
            Category parent=categoryDao.findByIdLock(category.getParent().getId());
            parent.setLeaf(true);
            categoryDao.save(parent);
        }
        categoryDao.delete(category);
    }

    @Override
    public List<CategoryVO> getCategoryVOList(GetCategoryListDTO request) {
        QCategory e=QCategory.category;

        Predicate p=getPredicate(request);

        List<CategoryVO> list=queryList(e,p,e.sort.asc(),categoryVOConvert);

        return toLevel(list);
    }

    private List<CategoryVO> toLevel(List<CategoryVO> all){
        Map<Long,CategoryVO> map=all.stream().collect(Collectors.toMap(t->t.getId(), t->t));
        List<CategoryVO> list=new ArrayList<>();
        for(CategoryVO vo:all){
            if(vo.getParentId()!=null){
                CategoryVO parent=map.get(vo.getParentId());
                if(parent.getChildren()==null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(vo);
            }else{
                list.add(vo);
            }
        }
        return list;
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

        Predicate p=getPredicate(request);

        return queryPage(e,p,categoryVOConvert,pageable);
    }


    private Predicate getPredicate(GetCategoryListDTO request){
        QCategory e=QCategory.category;

        Predicate p=null;

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId()).and(p);
        }

        if(request.getTargetId()!=null){
            p=e.targetId.eq(request.getTargetId()).and(p);
        }

        if(StringUtil.isNotEmpty(request.getType())){
            p=e.type.eq(request.getType()).and(p);
        }

        return p;
    }


    @Transactional
    @Override
    public void sort(List<Long> ids) {
        for(int i=0;i<ids.size();i++){
            categoryDao.setSort(ids.get(i),i);
        }
    }


}
