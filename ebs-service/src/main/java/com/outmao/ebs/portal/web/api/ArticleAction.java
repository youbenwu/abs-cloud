package com.outmao.ebs.portal.web.api;


import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.portal.dto.ArticleDTO;
import com.outmao.ebs.portal.dto.GetArticleCategoryListDTO;
import com.outmao.ebs.portal.dto.GetArticleListDTO;
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


@Api(value = "portal-article", tags = "门户-文章")
@RestController
@RequestMapping("/api/portal/article")
public class ArticleAction {

    @Autowired
    private ArticleService articleService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取文章类别列表", notes = "获取文章类别列表")
    @PostMapping("/category/list")
    public List<ArticleCategoryVO> getArticleCategoryVOList(GetArticleCategoryListDTO request){
        return articleService.getArticleCategoryVOList(request);
    }

    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存文章", notes = "保存文章")
    @PostMapping("/save")
    public void saveArticle(@RequestBody ArticleDTO request){
        articleService.saveArticle(request);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取文章信息", notes = "获取文章信息")
    @PostMapping("/get")
    public ArticleVO getArticleVOById(Long id){
        return articleService.getArticleVOById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取文章信息", notes = "获取文章信息")
    @PostMapping("/getByCode")
    public ArticleVO getArticleVOByCode(String code){
        return articleService.getArticleVOByCode(code);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取文章列表", notes = "获取文章列表")
    @PostMapping("/page")
    public Page<ArticleVO> getArticleVOPage(GetArticleListDTO request, Pageable pageable){
        return articleService.getArticleVOPage(request,pageable);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取文章浏览记录", notes = "获取文章浏览记录")
    @PostMapping("/browse/page")
    public Page<SubjectBrowseVO<ArticleVO>> getArticleBrowseVOPage(Long userId, Pageable pageable){
        return articleService.getArticleBrowseVOPage(userId,pageable);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取文章收藏列表", notes = "获取文章收藏列表")
    @PostMapping("/collection/page")
    public Page<SubjectCollectionVO<ArticleVO>> getArticleCollectionVOPage(Long userId, Pageable pageable){
        return articleService.getArticleCollectionVOPage(userId,pageable);
    }



}
