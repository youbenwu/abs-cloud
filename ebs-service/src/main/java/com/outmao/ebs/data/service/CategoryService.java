package com.outmao.ebs.data.service;

import com.outmao.ebs.data.dto.CategoryDTO;
import com.outmao.ebs.data.dto.GetCategoryListDTO;
import com.outmao.ebs.data.dto.SetCategoryStatusDTO;
import com.outmao.ebs.data.entity.Category;
import com.outmao.ebs.data.vo.CategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    //Category
    public Category saveCategory(CategoryDTO request);

    public Category setCategoryStatus(SetCategoryStatusDTO request);

    public void deleteCategoryById(Long id);

    public List<CategoryVO> getCategoryVOList(GetCategoryListDTO request);

    public Page<CategoryVO> getCategoryVOPage(GetCategoryListDTO request, Pageable pageable);

    public void sort(List<Long> ids);

}
