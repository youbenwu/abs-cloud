package com.outmao.ebs.data.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.data.domain.CategoryDomain;
import com.outmao.ebs.data.dto.CategoryDTO;
import com.outmao.ebs.data.dto.GetCategoryListDTO;
import com.outmao.ebs.data.dto.SetCategoryStatusDTO;
import com.outmao.ebs.data.entity.Category;
import com.outmao.ebs.data.service.CategoryService;
import com.outmao.ebs.data.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

    @Autowired
    private CategoryDomain categoryDomain;

    //Category

    @Override
    public Category saveCategory(CategoryDTO request) {
        return categoryDomain.saveCategory(request);
    }

    @Override
    public Category setCategoryStatus(SetCategoryStatusDTO request) {
        return categoryDomain.setCategoryStatus(request);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryDomain.deleteCategoryById(id);
    }

    @Override
    public List<CategoryVO> getCategoryVOList(GetCategoryListDTO request) {
        return categoryDomain.getCategoryVOList(request);
    }


    @Override
    public Page<CategoryVO> getCategoryVOPage(GetCategoryListDTO request, Pageable pageable) {
        return categoryDomain.getCategoryVOPage(request,pageable);
    }


    @Override
    public void sort(List<Long> ids) {
        categoryDomain.sort(ids);
    }


}
