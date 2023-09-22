package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.service.ArticleService;
import com.outmao.ebs.portal.vo.ArticleCategoryVO;
import com.outmao.ebs.portal.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AccessPermissionGroup(title="门户",url="/portal",name="",children = {
        @AccessPermissionParent(title = "文章管理",url = "/portal/article",name = "",children = {
                @AccessPermission(title = "保存文章信息",url = "/portal/article",name = "save"),
                @AccessPermission(title = "删除文章信息",url = "/portal/article",name = "delete"),
                @AccessPermission(title = "读取文章信息",url = "/portal/article",name = "read"),
                @AccessPermission(title = "设置文章状态",url = "/portal/article",name = "status"),
        }),
        @AccessPermissionParent(title = "文章类别管理",url = "/portal/article/category",name = "",children = {
                @AccessPermission(title = "保存文章类别",url = "/portal/article/category",name = "save"),
                @AccessPermission(title = "删除文章类别",url = "/portal/article/category",name = "delete"),
                @AccessPermission(title = "读取文章类别",url = "/portal/article/category",name = "read"),
                @AccessPermission(title = "设置文章类别状态",url = "/portal/article/category",name = "status"),
        }),
})


@Api(value = "admin-portal-article", tags = "后台-门户-文章")
@RestController
@RequestMapping("/api/admin/portal/article")
public class ArticleAdminAction {

	@Autowired
    private ArticleService articleService;


    @PreAuthorize("hasPermission('/portal/article/category','save')")
    @ApiOperation(value = "保存文章类别", notes = "保存文章类别")
    @PostMapping("/category/save")
    public void saveArticleCategory(ArticleCategoryDTO request){
        articleService.saveArticleCategory(request);
    }

    @PreAuthorize("hasPermission('/portal/article/category','status')")
    @ApiOperation(value = "设置文章类别状态", notes = "设置文章类别状态")
    @PostMapping("/category/setStatus")
    public void setArticleCategoryStatus(SetArticleCategoryStatusDTO request){
        articleService.setArticleCategoryStatus(request);
    }

    @PreAuthorize("hasPermission('/portal/article/category','delete')")
    @ApiOperation(value = "删除文章类别", notes = "删除文章类别")
    @PostMapping("/category/delete")
    public void deleteArticleCategoryById(Long id){
        articleService.deleteArticleCategoryById(id);
    }

    @PreAuthorize("hasPermission('/portal/article/category','read')")
    @ApiOperation(value = "获取文章类别列表", notes = "获取文章类别列表")
    @PostMapping("/category/list")
    public List<ArticleCategoryVO> getArticleCategoryVOList(GetArticleCategoryListDTO request){
        return articleService.getArticleCategoryVOList(request);
    }


    @PreAuthorize("hasPermission('/portal/article','save')")
    @ApiOperation(value = "保存文章", notes = "保存文章")
    @PostMapping("/save")
    public void saveArticle(@RequestBody ArticleDTO request){
        articleService.saveArticle(request);
    }

    @PreAuthorize("hasPermission('/portal/article','status')")
    @ApiOperation(value = "设置文章状态", notes = "设置文章状态")
    @PostMapping("/setStatus")
    public void setArticleStatus(SetArticleStatusDTO request){
        articleService.setArticleStatus(request);
    }

    @PreAuthorize("hasPermission('/portal/article','read')")
    @ApiOperation(value = "获取文章信息", notes = "获取文章信息")
    @PostMapping("/get")
    public ArticleVO getArticleVOById(Long id){
        return articleService.getArticleVOById(id);
    }

    @PreAuthorize("hasPermission('/portal/article','read')")
    @ApiOperation(value = "获取文章列表", notes = "获取文章列表")
    @PostMapping("/page")
    public Page<ArticleVO> getArticleVOPage(@RequestBody GetArticleListDTO request, Pageable pageable){
        return articleService.getArticleVOPage(request,pageable);
    }



}
