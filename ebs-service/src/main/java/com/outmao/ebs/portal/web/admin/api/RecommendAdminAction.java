package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.dto.RecommendDTO;
import com.outmao.ebs.portal.entity.Recommend;
import com.outmao.ebs.portal.service.RecommendService;
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


@AccessPermissionGroup(title="门户",url="/portal",name="",children = {
        @AccessPermissionParent(title = "推荐管理",url = "/portal/recommend",name = "",children = {
                @AccessPermission(title = "保存推荐",url = "/portal/recommend",name = "save"),
                @AccessPermission(title = "删除推荐",url = "/portal/recommend",name = "delete"),
                @AccessPermission(title = "读取推荐",url = "/portal/recommend",name = "read"),
        }),
})


@Api(value = "account-portal-recommend", tags = "后台-门户-推荐")
@RestController
@RequestMapping("/api/admin/portal/recommend")
public class RecommendAdminAction {

	@Autowired
    private RecommendService recommendService;

    @PreAuthorize("hasPermission('/portal/recommend','save')")
    @ApiOperation(value = "保存推荐", notes = "保存推荐")
    @PostMapping("/save")
    public void saveRecommend(@RequestBody RecommendDTO request) {
         recommendService.saveRecommend(request);
    }

    @PreAuthorize("hasPermission('/portal/recommend','delete')")
    @ApiOperation(value = "删除推荐", notes = "删除推荐")
    @PostMapping("/delete")
    public void deleteRecommendById(Long id) {
        recommendService.deleteRecommendById(id);
    }

    @PreAuthorize("hasPermission('/portal/recommend','read')")
    @ApiOperation(value = "获取推荐列表", notes = "获取推荐列表")
    @PostMapping("/page")
    public Page<Recommend> getRecommendPage(GetRecommendListDTO request, Pageable pageable) {
        return recommendService.getRecommendPage(request,pageable);
    }


}
