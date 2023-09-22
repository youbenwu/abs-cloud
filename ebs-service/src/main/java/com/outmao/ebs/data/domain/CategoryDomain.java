package com.outmao.ebs.data.domain;


import com.outmao.ebs.data.dto.CategoryDTO;
import com.outmao.ebs.data.dto.GetCategoryListDTO;
import com.outmao.ebs.data.dto.SetCategoryStatusDTO;
import com.outmao.ebs.data.entity.Category;
import com.outmao.ebs.data.vo.CategoryVO;
import com.outmao.ebs.data.vo.SimpleCategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface CategoryDomain {


    public Category saveCategory(CategoryDTO request);

    public Category setCategoryStatus(SetCategoryStatusDTO request);

    public void deleteCategoryById(Long id);

    public List<CategoryVO> getCategoryVOList(GetCategoryListDTO request);

    public List<CategoryVO> getCategoryVOListByIdIn(Collection<Long> idIn);

    public List<SimpleCategoryVO> getSimpleCategoryVOListByIdIn(Collection<Long> idIn);

    public Page<CategoryVO> getCategoryVOPage(GetCategoryListDTO request, Pageable pageable);


}
