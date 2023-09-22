package com.outmao.ebs.data.web.api;


import com.outmao.ebs.data.dto.GetCategoryListDTO;
import com.outmao.ebs.data.service.CategoryService;
import com.outmao.ebs.data.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;





@Api(value = "data-category", tags = "数据-分类")
@RestController
@RequestMapping("/api/data/category")
public class CategoryAction {

	@Autowired
    private CategoryService categoryService;


    //Category

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取分类列表", notes = "获取分类列表")
    @PostMapping("/list")
    public List<CategoryVO> getCategoryVOList(GetCategoryListDTO request) {
        return categoryService.getCategoryVOList(request);
    }




}
