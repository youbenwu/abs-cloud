package com.outmao.ebs.data.web.admin.api;


import com.outmao.ebs.data.dto.CategoryDTO;
import com.outmao.ebs.data.dto.GetCategoryListDTO;
import com.outmao.ebs.data.dto.SetCategoryStatusDTO;
import com.outmao.ebs.data.service.CategoryService;
import com.outmao.ebs.data.vo.CategoryVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@AccessPermissionGroup(title="数据",url="/data",name="",children = {
        @AccessPermissionParent(title = "分类管理",url = "/data/category",name = "",children = {
                @AccessPermission(title = "保存分类",url = "/data/category",name = "save"),
                @AccessPermission(title = "删除分类",url = "/data/category",name = "delete"),
                @AccessPermission(title = "读取分类",url = "/data/category",name = "read"),
                @AccessPermission(title = "审核分类",url = "/data/category",name = "status"),
        }),
})


@Api(value = "admin-data-category", tags = "后台-数据-分类")
@RestController
@RequestMapping("/api/admin/data/category")
public class CategoryAdminAction {

	@Autowired
    private CategoryService categoryService;


    //Category
    @PreAuthorize("hasPermission('/data/category','save')")
    @ApiOperation(value = "保存分类", notes = "保存分类")
    @PostMapping("/save")
    public void saveArticleCategory(CategoryDTO request){
        categoryService.saveCategory(request);
    }

    @PreAuthorize("hasPermission('/data/category','status')")
    @ApiOperation(value = "设置分类状态", notes = "设置分类状态")
    @PostMapping("/setStatus")
    public void setCategoryStatus(SetCategoryStatusDTO request){
        categoryService.setCategoryStatus(request);
    }

    @PreAuthorize("hasPermission('/data/category','delete')")
    @ApiOperation(value = "删除分类", notes = "删除分类")
    @PostMapping("/delete")
    public void deleteCategoryById(Long id){
        categoryService.deleteCategoryById(id);
    }

    @PreAuthorize("hasPermission('/data/category','read')")
    @ApiOperation(value = "获取分类列表", notes = "获取分类列表")
    @PostMapping("/list")
    public List<CategoryVO> getCategoryVOList(GetCategoryListDTO request){
        return categoryService.getCategoryVOList(request);
    }

    @PreAuthorize("hasPermission('/data/category','read')")
    @ApiOperation(value = "获取分类列表", notes = "获取分类列表")
    @PostMapping("/page")
    public Page<CategoryVO> getCategoryVOPage(GetCategoryListDTO request, Pageable pageable){
        return categoryService.getCategoryVOPage(request,pageable);
    }




}
